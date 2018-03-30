package com.expertsoft.web.form;

import com.expertsoft.core.service.component.ShoppingCart;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

public class UpdateCartForm {

    @Valid
    private final Map<String, UpdateCartItem> items = new HashMap<>();

    public static UpdateCartForm of(ShoppingCart cart) {
        UpdateCartForm result = new UpdateCartForm();

        cart.getItems().forEach((key, value) ->
                result.items.put(key.toString(), UpdateCartItem.of(value)));

        return result;
    }

    public Map<String, UpdateCartItem> getItems() {
        return items;
    }
}
