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

    public ShoppingCartService(ProductDao productDao, ShoppingCart shoppingCart) {
        this.productDao = productDao;
        this.shoppingCart = shoppingCart;
    }

    @Autowired
    public ShoppingCartService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void addToCart(long productId, int quantity) {
        Map<Long, Integer> items = shoppingCart.getItems();
        Integer oldQuantity = items.putIfAbsent(productId, quantity);
        if (oldQuantity != null) {
            items.put(productId, oldQuantity + quantity);
        }
    }

    public void removeFromCart(long productId) {
        shoppingCart.getItems().entrySet().removeIf(e -> e.getKey() == productId);
    }

    public void updateCart(Map<Long, Integer> items) {
        Map<Long, Integer> cartItems = shoppingCart.getItems();
        items.forEach(cartItems::replace);
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
