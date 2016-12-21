package com.expertsoft.core.service.component;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
}
