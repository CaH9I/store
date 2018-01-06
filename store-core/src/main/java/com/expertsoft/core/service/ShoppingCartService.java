package com.expertsoft.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expertsoft.core.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.service.component.ShoppingCart;
import com.expertsoft.core.service.component.ShoppingCartView;

@Service
public class ShoppingCartService {

    private ShoppingCart shoppingCart;
    private final ProductRepository productRepository;

    public ShoppingCartService(ProductRepository productRepository, ShoppingCart shoppingCart) {
        this.productRepository = productRepository;
        this.shoppingCart = shoppingCart;
    }

    @Autowired
    public ShoppingCartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        List<MobilePhone> phones = productRepository.findByIdIn(cartItems.keySet());

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
