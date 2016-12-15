package com.expertsoft.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expertsoft.core.service.ShoppingCartEntry;
import com.expertsoft.core.service.StoreService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private StoreService storeService;
	
	@Autowired
	public CartController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@RequestMapping(method=GET)
	public String cart(Model model) {
		model.addAttribute(storeService.getShoppingCart());
		return "cart";
	}
	
	@RequestMapping(value="/add-to-cart", method=POST)
	public String addToCart(@Valid ShoppingCartEntry entry, Errors errors, Model model) {
		storeService.addToCart(entry, errors);
		model.addAttribute(storeService.getShoppingCart());
		model.addAttribute("errors", errors);
		return "json/addToCart";
	}
	
	@RequestMapping(method=POST)
	public String delete(@RequestParam long productId, Model model) {
		storeService.removeFromCart(productId);
		model.addAttribute(storeService.getShoppingCart());
		return "cart";
	}

}
