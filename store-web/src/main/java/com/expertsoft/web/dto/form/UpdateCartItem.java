package com.expertsoft.web.dto.form;

import javax.validation.constraints.Min;

public class UpdateCartItem {

    @Min(value = 1)
    private int quantity;

    public static UpdateCartItem of(int quantity) {
        var cartItem = new UpdateCartItem();
        cartItem.quantity = quantity;
        return cartItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}