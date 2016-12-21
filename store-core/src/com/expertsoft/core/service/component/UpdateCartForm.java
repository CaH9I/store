package com.expertsoft.core.service.component;

import java.util.HashMap;
import java.util.Map;

import com.expertsoft.core.model.entity.CommerceItem;

public class UpdateCartForm {
	
	private Map<String, String> items = new HashMap<>();
	
	public UpdateCartForm() {}
	
	public UpdateCartForm(ShoppingCart cart) {
		for (CommerceItem item : cart.getOrder().getCommerceItems()) {
			String productId = String.valueOf(item.getPhone().getId());
			String quantity = String.valueOf(item.getQuantity());
			items.put(productId, quantity);
		}
	}

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

}
