package com.expertsoft.web.dto.form;

import com.expertsoft.core.commerce.ShoppingCart;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class UpdateCartForm {

    /**
     * key: {@link com.expertsoft.core.model.entity.MobilePhone#id}
     * value: quantity
     */
    private final Map<Long, @NotNull @Min(value = 1) Integer> items = new HashMap<>();

    public static UpdateCartForm from(ShoppingCart cart) {
        UpdateCartForm result = new UpdateCartForm();
        result.items.putAll(cart.getItems());
        return result;
    }

    public Map<Long, Integer> getItems() {
        return items;
    }
}
