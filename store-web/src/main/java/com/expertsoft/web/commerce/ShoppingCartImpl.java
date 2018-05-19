package com.expertsoft.web.commerce;

import com.expertsoft.core.commerce.AbstractShoppingCart;
import com.expertsoft.core.commerce.ShoppingCartView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class ShoppingCartImpl extends AbstractShoppingCart {

    private final ShoppingCartView view;

    @Autowired
    public ShoppingCartImpl(ShoppingCartView view) {
        this.view = view;
    }

    @Override
    public ShoppingCartView getView() {
        return view;
    }
}
