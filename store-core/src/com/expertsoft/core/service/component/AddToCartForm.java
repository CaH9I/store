package com.expertsoft.core.service.component;

import javax.validation.constraints.Min;

public class AddToCartForm {

	private long productId;
	
	@Min(value=1, message="{validation.addToCart.quantity}")
	private int quantity;
	
	public AddToCartForm() {}
	
	public AddToCartForm(long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

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
