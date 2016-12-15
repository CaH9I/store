package com.expertsoft.core.service;

import javax.validation.constraints.Min;

public class ShoppingCartEntry {

	private long productId;
	
	@Min(value=1, message="{validation.addToCart.quantity}")
	private int quantity;
	
	public ShoppingCartEntry() {}
	
	public ShoppingCartEntry(long productId, int quantity) {
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
