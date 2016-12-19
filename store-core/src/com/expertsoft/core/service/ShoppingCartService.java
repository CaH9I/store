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
		int quantity = Integer.parseInt(form.getQuantity());
		shoppingCart.add(phone, quantity);
	}
	
	public void removeFromCart(long productId) {
		shoppingCart.remove(productId);
	}
	
	public void updateCart(UpdateCartForm form) {
		for (Map.Entry<MobilePhone, Integer> cartEntry : shoppingCart.getItems().entrySet()) {
			String productId = String.valueOf(cartEntry.getKey().getId());
			Integer quantity = Integer.valueOf(form.getItems().get(productId));
			if (quantity != null) {
				cartEntry.setValue(quantity);
			}
		}
	}
}
