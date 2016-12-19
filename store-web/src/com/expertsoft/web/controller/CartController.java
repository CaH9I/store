package com.expertsoft.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.core.service.component.AddToCartForm;
import com.expertsoft.core.service.component.UpdateCartForm;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private ShoppingCartService cartService;
	
	@Autowired
	public CartController(ShoppingCartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping
	public String cart(Model model) {
		model.addAttribute(cartService.getShoppingCart());
		model.addAttribute(new UpdateCartForm(cartService.getShoppingCart()));
		return "cart";
	}
	
	@PostMapping("/add-to-cart")
	public String addToCart(@Valid AddToCartForm form, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("errors", errors);
			return "json/addToCartError";
		}
		cartService.addToCart(form);
		model.addAttribute(cartService.getShoppingCart());
		return "json/addToCartSuccess";
	}
	
	@PostMapping(params="productToRemoveId")
	public String delete(@RequestParam long productToRemoveId, Model model) {
		cartService.removeFromCart(productToRemoveId);
		return "redirect:/cart";
	}
	
	@PostMapping
	public String update(@ModelAttribute UpdateCartForm form, Model model) {
		cartService.updateCart(form);
		return "redirect:/cart";
	}

}
