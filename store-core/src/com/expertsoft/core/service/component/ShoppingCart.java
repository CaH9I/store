package com.expertsoft.core.service.component;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import java.util.List;
import java.util.ListIterator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;

@Component
@Scope(value=SCOPE_SESSION, proxyMode=TARGET_CLASS)
public class ShoppingCart {
	
	private Order order = new Order();
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}

	public void add(MobilePhone phone, int quantity) {
		List<CommerceItem> items = order.getCommerceItems();
		// check if the item is already in cart
		for (CommerceItem item : order.getCommerceItems()) {
			if (item.getPhone().equals(phone)) {
				item.setQuantity(item.getQuantity() + quantity);
				return;
			}
		}
		// cart doesn't contain this item
		items.add(phone, quantity);
	}

	public void remove(long productId) {
		ListIterator<CommerceItem> it = order.getCommerceItems().listIterator();
		while (it.hasNext()) {
			CommerceItem item = it.next();
			if (item.getPhone().getId() == productId) {
				it.remove();
				break;
			}
		}
	}

	public int getNumberOfItems() {
		return order.getCommerceItems().stream().mapToInt(ci -> ci.getQuantity()).sum();
	}

}
