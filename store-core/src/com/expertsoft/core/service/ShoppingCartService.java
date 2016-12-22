package com.expertsoft.core.service;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.AddToCartForm;
import com.expertsoft.core.service.component.ShoppingCart;
import com.expertsoft.core.service.component.UpdateCartForm;

@Service
public class ShoppingCartService {

    private ShoppingCart shoppingCart;
    private ProductService productService;

    @Autowired
    public ShoppingCartService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void addToCart(AddToCartForm form) {
        MobilePhone phone = productService.getById(form.getProductId());
        int quantity = Integer.parseInt(form.getQuantity());

        List<CommerceItem> items = shoppingCart.getOrder().getCommerceItems();
        // check if the item is already in cart
        for (CommerceItem ci : items) {
            if (ci.getPhone().equals(phone)) {
                ci.setQuantity(ci.getQuantity() + quantity);
                return;
            }
        }
        // cart doesn't contain this item
        items.add(new CommerceItem(phone, quantity, phone.getPrice()));
    }

    public void removeFromCart(long productId) {
        ListIterator<CommerceItem> it = shoppingCart.getOrder().getCommerceItems().listIterator();
        while (it.hasNext()) {
            CommerceItem ci = it.next();
            if (ci.getPhone().getId() == productId) {
                it.remove();
                break;
            }
        }
    }

    public void updateCart(UpdateCartForm form) {
        Map<String, String> updatedItems = form.getItems();
        for (CommerceItem ci : shoppingCart.getOrder().getCommerceItems()) {
            String productId = String.valueOf(ci.getPhone().getId());
            int newQuantity = Integer.parseInt(updatedItems.get(productId));
            ci.setQuantity(newQuantity);
        }
    }

    public void clearCart() {
        shoppingCart.setLastOrder(shoppingCart.getOrder());
        shoppingCart.setOrder(new Order());
    }
}
