package com.expertsoft.core.service;

import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.service.component.ShoppingCart;
import com.expertsoft.core.service.component.ShoppingCartView;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService {

    private final ShoppingCart shoppingCart;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCart shoppingCart, ProductService productService) {
        this.productService = productService;
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
        List<MobilePhone> phones = productService.findAllById(cartItems.keySet());

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
