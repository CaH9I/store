package com.expertsoft.core.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.service.component.AddToCartForm;
import com.expertsoft.core.service.component.ShoppingCart;
import com.expertsoft.core.service.component.UpdateCartForm;

@Service
public class ShoppingCartService {
	
	private ShoppingCart shoppingCart;
	private ProductService productService;
	
	@Autowired
	public ShoppingCartService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	public void addToCart(AddToCartForm form) {
		MobilePhone phone = productService.getById(form.getProductId());
		shoppingCart.add(phone, form.getQuantity());
	}
	
	public void removeFromCart(long productId) {
		shoppingCart.remove(productId);
	}
	
	public void updateCart(UpdateCartForm form) {
		for (Map.Entry<MobilePhone, Integer> entry : shoppingCart.getItems().entrySet()) {
			int quantity = form.getItems().get(String.valueOf(entry.getKey().getId()));
			entry.setValue(quantity);
		}
	}
}
