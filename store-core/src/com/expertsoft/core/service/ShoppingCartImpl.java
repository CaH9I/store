package com.expertsoft.core.service;

import static org.springframework.context.annotation.ScopedProxyMode.INTERFACES;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.expertsoft.core.model.entity.MobilePhone;

@Component
@Scope(value=SCOPE_SESSION, proxyMode=INTERFACES)
public class ShoppingCartImpl implements ShoppingCart {
	
	private Map<MobilePhone, Integer> items = new HashMap<MobilePhone, Integer>();

	@Override
	public void add(MobilePhone phone, int quantity) {
		for (Map.Entry<MobilePhone, Integer> entry : items.entrySet()) {
			if (entry.getKey().equals(phone)) {
				entry.setValue(entry.getValue() + quantity);
				return;
			}
		}

		items.put(phone, quantity);
	}

	@Override
	public void remove(long productid) {
		for (Map.Entry<MobilePhone, Integer> entry : items.entrySet()) {
			MobilePhone phone = entry.getKey();
			if (phone.getId() == productid) {
				items.remove(phone);
				break;
			}
		}
	}

	@Override
	public Map<MobilePhone, Integer> getItems() {
		return items;
	}

	@Override
	public int getNumberOfItems() {
		int result = 0;
		for (Map.Entry<MobilePhone, Integer> entry : items.entrySet()) {
			result += entry.getValue();
		}
		return result;
	}

	@Override
	public double getSubtotal() {
		double result = 0;
		for (Map.Entry<MobilePhone, Integer> entry : items.entrySet()) {
			result += entry.getKey().getPrice() * entry.getValue();
		}
		return result;
	}

}
