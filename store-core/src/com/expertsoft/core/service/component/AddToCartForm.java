package com.expertsoft.core.service.component;

import javax.validation.constraints.Min;

public class AddToCartForm {

    private long productId;

    @Min(value = 1, message = "{cart.quantity.notPositive}")
    private Integer quantity;

    public AddToCartForm() {
    }

    public AddToCartForm(long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
