package com.expertsoft.core.service.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.ProductService;

@Service
public class OrderService {
	
	private ProductService productService;
	
	@Autowired
	public OrderService(ProductService productService) {
		this.productService = productService;
	}
	
	public void updateOrder(Order order) {
		Map<MobilePhone, Integer> items = new HashMap<>();
		double subtotal = 0;
		
		for (Map.Entry<MobilePhone, Integer> entry : order.getItems().entrySet()) {
			int quantity = entry.getValue();
			MobilePhone phone = productService.getById(entry.getKey().getId());
			subtotal += phone.getPrice() * quantity;
			items.put(phone, quantity);
		}
		
		order.setSubtotal(subtotal);
		order.setItems(items);
	}

}
