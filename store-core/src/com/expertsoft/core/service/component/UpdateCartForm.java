package com.expertsoft.core.service.component;

import java.util.HashMap;
import java.util.Map;

import com.expertsoft.core.model.entity.MobilePhone;

public class UpdateCartForm {
	
	private Map<String, Integer> items = new HashMap<String, Integer>();
	
	public UpdateCartForm() {}
	
	public UpdateCartForm(ShoppingCart cart) {
		for (Map.Entry<MobilePhone, Integer> entry : cart.getItems().entrySet()) {
			String id = String.valueOf(entry.getKey().getId());
			int quantity = entry.getValue();
			items.put(id, quantity);
		}
	}

	public Map<String, Integer> getItems() {
		return items;
	}

	public void setItems(Map<String, Integer> items) {
		this.items = items;
	}

}
