package com.expertsoft.web.form;

import javax.validation.constraints.Min;

public class AddToCartForm {

    private long productId;

    @Min(value = 1)
    private int quantity;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
