package com.expertsoft.core.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.service.component.AddToCartForm;
import com.expertsoft.core.service.component.ShoppingCart;
import com.expertsoft.core.service.component.UpdateCartForm;

@Service
public class ShoppingCartService {

    private ShoppingCart shoppingCart;

    @Autowired
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void addToCart(AddToCartForm form) {
        long productId = form.getProductId();
        int quantity = form.getQuantity();

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

    public void updateCart(UpdateCartForm form) {
        Map<Long, Integer> cartItems = shoppingCart.getItems();

        form.getItems().entrySet().forEach(entry -> {
            long productId = Long.parseLong(entry.getKey());
            int quantity = entry.getValue().getQuantity();
            cartItems.replace(productId, quantity);
        });
    }

    public void clearCart() {
        shoppingCart.setItems(new HashMap<>());
    }
}
