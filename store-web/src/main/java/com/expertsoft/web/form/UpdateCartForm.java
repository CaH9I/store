package com.expertsoft.web.form;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.expertsoft.core.service.component.ShoppingCart;

public class UpdateCartForm {

    @Valid
    private Map<String, UpdateCartItem> items = new HashMap<>();

    public UpdateCartForm() {}

    public UpdateCartForm(ShoppingCart cart) {
        cart.getItems().forEach((key, value) -> items.put(key.toString(), new UpdateCartItem(value)));
    }

    public Map<String, UpdateCartItem> getItems() {
        return items;
    }

    public void setItems(Map<String, UpdateCartItem> items) {
        this.items = items;
    }

}
