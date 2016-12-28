package com.expertsoft.core.service.component;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.expertsoft.core.model.entity.CommerceItem;

public class UpdateCartForm {

    @Valid
    private Map<String, UpdateCartItem> items = new HashMap<>();

    public UpdateCartForm() {}

    public UpdateCartForm(ShoppingCart cart) {
        for (CommerceItem item : cart.getOrder().getCommerceItems()) {
            String productId = String.valueOf(item.getPhone().getId());
            items.put(productId, new UpdateCartItem(item.getQuantity()));
        }
    }

    public Map<String, UpdateCartItem> getItems() {
        return items;
    }

    public void setItems(Map<String, UpdateCartItem> items) {
        this.items = items;
    }

}
