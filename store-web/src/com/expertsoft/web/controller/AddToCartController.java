package com.expertsoft.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.core.service.component.AddToCartForm;

@Controller
@RequestMapping("/cart/add-to-cart")
public class AddToCartController {
	
	private ShoppingCartService cartService;
	
	@Autowired
	public AddToCartController(ShoppingCartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping
	public String addToCart(@Valid AddToCartForm form, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("errors", errors);
			return "json/addToCartError";
		}
		cartService.addToCart(form);
		model.addAttribute(cartService.getShoppingCart());
		return "json/addToCartSuccess";
	}

}
