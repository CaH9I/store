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
import com.expertsoft.core.service.ShoppingCartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private ShoppingCartService cartService;
	
	@Autowired
	public CartController(ShoppingCartService cartService) {
		this.cartService = cartService;
	}
	
	@RequestMapping(method=GET)
	public String cart(Model model) {
		model.addAttribute(cartService.getShoppingCart());
		return "cart";
	}
	
	@RequestMapping(value="/add-to-cart", method=POST)
	public String addToCart(@Valid ShoppingCartEntry entry, Errors errors, Model model) {
		cartService.addToCart(entry, errors);
		model.addAttribute(cartService.getShoppingCart());
		model.addAttribute("errors", errors);
		return "json/addToCart";
	}
	
	@RequestMapping(method=POST)
	public String delete(@RequestParam long productId, Model model) {
		cartService.removeFromCart(productId);
		model.addAttribute(cartService.getShoppingCart());
		return "cart";
	}

}
