package com.expertsoft.web.commerce;

import com.expertsoft.core.commerce.AbstractShoppingCartView;
import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.HashMap;

import static java.util.Collections.unmodifiableMap;

@Component
@RequestScope
public class ShoppingCartViewImpl extends AbstractShoppingCartView implements InitializingBean {

    private final ShoppingCart cart;
    private final ProductService productService;

    @Autowired
    public ShoppingCartViewImpl(ShoppingCart cart, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
    }

    @Override
    public void afterPropertiesSet() {
        var cartItems = cart.getItems();
        var phones = productService.findAllById(cartItems.keySet());
        var items = new HashMap<MobilePhone, Integer>();

        for (var phone : phones) {
            var quantity = cartItems.get(phone.getId());
            items.put(phone, quantity);
            numberOfItems += quantity;
            subtotal += phone.getPrice() * quantity;
        }

        this.items = unmodifiableMap(items);
    }
}
