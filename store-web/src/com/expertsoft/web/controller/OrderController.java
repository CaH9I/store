package com.expertsoft.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.ShoppingCartService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private ShoppingCartService cartService;
	
	@Autowired
	public OrderController(ShoppingCartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping
	public String order(Model model) {
		model.addAttribute(cartService.getShoppingCart());
		return "order";
	}
}
