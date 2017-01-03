package com.expertsoft.core.service.component;

import javax.validation.constraints.Min;

public class UpdateCartItem {

    @Min(value = 1, message = "{cart.quantity.notPositive}")
    private int quantity;

    public UpdateCartItem() {}

    public UpdateCartItem(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
