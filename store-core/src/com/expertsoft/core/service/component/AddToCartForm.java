package com.expertsoft.core.service.component;

import com.expertsoft.core.validator.IntegerNumber;

public class AddToCartForm {

    private long productId;

    @IntegerNumber(min = 1, message = "{addToCart.quantity.invalid}")
    private String quantity;

    public AddToCartForm() {
    }

    public AddToCartForm(long productId, String quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
