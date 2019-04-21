package com.expertsoft.web.event;

import com.expertsoft.core.commerce.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PlaceOrderEventListener {

    private final ShoppingCart cart;

    @Autowired
    public PlaceOrderEventListener(ShoppingCart cart) {
        this.cart = cart;
    }

    @TransactionalEventListener(PlaceOrderEvent.class)
    public void afterPlaceOrder() {
        cart.clear();
    }
}
