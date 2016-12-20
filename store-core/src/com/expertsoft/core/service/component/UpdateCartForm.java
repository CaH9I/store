package com.expertsoft.core.service.component;

import java.util.HashMap;
import java.util.Map;

import com.expertsoft.core.model.entity.MobilePhone;

public class UpdateCartForm {
	
	private Map<String, String> items = new HashMap<>();
	
	public UpdateCartForm() {}
	
	public UpdateCartForm(ShoppingCart cart) {
		for (Map.Entry<MobilePhone, Integer> entry : cart.getOrder().getItems().entrySet()) {
			String id = String.valueOf(entry.getKey().getId());
			String quantity = entry.getValue().toString();
			items.put(id, quantity);
		}
	}

	public Map<String, String> getItems() {
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

}
