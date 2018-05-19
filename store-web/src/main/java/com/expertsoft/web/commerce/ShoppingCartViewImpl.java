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
import java.util.List;
import java.util.Map;

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
        Map<Long, Integer> cartItems = cart.getItems();
        List<MobilePhone> phones = productService.findAllById(cartItems.keySet());
        Map<MobilePhone, Integer> items = new HashMap<>();

        for (MobilePhone phone : phones) {
            Integer quantity = cartItems.get(phone.getId());
            items.put(phone, quantity);
            numberOfItems += quantity;
            subtotal += phone.getPrice() * quantity;
        }

        this.items = unmodifiableMap(items);
    }
}
