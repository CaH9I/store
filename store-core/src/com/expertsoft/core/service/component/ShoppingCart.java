package com.expertsoft.core.service.component;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.expertsoft.core.model.entity.MobilePhone;

@Component
@Scope(value=SCOPE_SESSION, proxyMode=TARGET_CLASS)
public class ShoppingCart {
	
	private Map<MobilePhone, Integer> items = new HashMap<MobilePhone, Integer>();

	public void add(MobilePhone phone, int quantity) {
		for (Map.Entry<MobilePhone, Integer> entry : items.entrySet()) {
			if (entry.getKey().equals(phone)) {
				entry.setValue(entry.getValue() + quantity);
				return;
			}
		}

		items.put(phone, quantity);
	}

	public void remove(long productid) {
		for (Map.Entry<MobilePhone, Integer> entry : items.entrySet()) {
			MobilePhone phone = entry.getKey();
			if (phone.getId() == productid) {
				items.remove(phone);
				break;
			}
		}
	}

	public Map<MobilePhone, Integer> getItems() {
		return items;
	}
	
	public void setItems(Map<MobilePhone, Integer> items) {
		this.items = items;
	}

	public int getNumberOfItems() {
		int result = 0;
		for (Map.Entry<MobilePhone, Integer> entry : items.entrySet()) {
			result += entry.getValue();
		}
		return result;
	}

	public double getSubtotal() {
		double result = 0;
		for (Map.Entry<MobilePhone, Integer> entry : items.entrySet()) {
			result += entry.getKey().getPrice() * entry.getValue();
		}
		return result;
	}

}
