package com.expertsoft.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.expertsoft.core.model.entity.MobilePhone;

@Component
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
	
	public void addToCart(ShoppingCartEntry entry, Errors errors) {
		if (!errors.hasErrors()) {
			MobilePhone phone = productService.getById(entry.getProductId());
			shoppingCart.add(phone, entry.getQuantity());
		}
	}
	
	public void removeFromCart(long productId) {
		shoppingCart.remove(productId);
	}
}
