package com.expertsoft.core.service;

import java.util.Map;

import com.expertsoft.core.model.entity.MobilePhone;

public interface ShoppingCart {
	
	void add(MobilePhone phone, int quantity);
	
	void remove(long productId);
	
	Map<MobilePhone, Integer> getItems();
	
	int getNumberOfItems();
	
	double getSubtotal();
}
