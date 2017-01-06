package com.expertsoft.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.ProductDao;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.service.component.ShoppingCart;
import com.expertsoft.core.service.component.ShoppingCartView;

@Service
public class ShoppingCartService {

    private ShoppingCart shoppingCart;
    private ProductDao productDao;

    @Autowired
    public void setShoppingCart(ShoppingCart shoppingCart, ProductDao productDao) {
        this.shoppingCart = shoppingCart;
        this.productDao = productDao;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void addToCart(long productId, int quantity) {
        Map<Long, Integer> items = shoppingCart.getItems();
        // check if the item is already in cart
        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            if (entry.getKey().longValue() == productId) {
                entry.setValue(entry.getValue() + quantity);
                return;
            }
        }
        // cart doesn't contain this item
        items.put(productId, quantity);
    }

    public void removeFromCart(long productId) {
        shoppingCart.getItems().entrySet().removeIf(e -> e.getKey().longValue() == productId);
    }

    public void updateCart(Map<Long, Integer> items) {
        Map<Long, Integer> cartItems = shoppingCart.getItems();

        items.entrySet().forEach(entry -> {
            cartItems.replace(entry.getKey(), entry.getValue());
        });
    }

    public void clearCart() {
        shoppingCart.setItems(new HashMap<>());
    }

    public ShoppingCartView createShoppingCartView() {
        Map<Long, Integer> cartItems = shoppingCart.getItems();
        List<MobilePhone> phones = productDao.findByIds(cartItems.keySet());

        Map<MobilePhone, Integer> items = new HashMap<>();
        int numberOfItems = 0;
        double subtotal = 0;

        for (MobilePhone phone : phones) {
            Integer quantity = cartItems.get(phone.getId());
            items.put(phone, quantity);
            numberOfItems += quantity;
            subtotal += phone.getPrice() * quantity;
        }

        return new ShoppingCartView(items, numberOfItems, subtotal);
    }
}
