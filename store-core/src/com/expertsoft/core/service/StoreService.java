package com.expertsoft.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.expertsoft.core.model.JdbcProductDao;
import com.expertsoft.core.model.entity.MobilePhone;

@Component
public class StoreService {
	
	private JdbcProductDao productDao;
	private ShoppingCart shoppingCart;
	
	@Autowired
	public StoreService(JdbcProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Autowired
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	public List<MobilePhone> getAll() {
		return productDao.findAll();
	}
	
	public MobilePhone getById(long id) {
		return productDao.findById(id);
	}
	
	public void addToCart(ShoppingCartEntry entry, Errors errors) {
		if (!errors.hasErrors()) {
			MobilePhone phone = productDao.findById(entry.getProductId());
			shoppingCart.add(phone, entry.getQuantity());
		}
	}
	
	public void removeFromCart(long productId) {
		shoppingCart.remove(productId);
	}
}
