package com.expertsoft.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.core.service.component.UpdateCartForm;
import com.expertsoft.core.service.validator.UpdateCartValidator;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	private ShoppingCartService cartService;
	private UpdateCartValidator updateCartValidator;
	
	@Autowired
	public CartController(ShoppingCartService cartService, UpdateCartValidator updateCartValidator) {
		this.cartService = cartService;
		this.updateCartValidator = updateCartValidator;
	}
	
	@InitBinder("updateCartForm")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(updateCartValidator);
	}
	
	@GetMapping
	public String cart(Model model) {
		model.addAttribute(cartService.getShoppingCart());
		model.addAttribute(new UpdateCartForm(cartService.getShoppingCart()));
		return "cart";
	}
	
	@PostMapping(params="productToRemoveId")
	public String deleteProduct(@RequestParam long productToRemoveId, Model model) {
		cartService.removeFromCart(productToRemoveId);
		return "redirect:/cart";
	}
	
	@PostMapping
	public String updateCart(@ModelAttribute @Valid UpdateCartForm form, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute(cartService.getShoppingCart());
			model.addAttribute(form);
			return "cart";
		}
		cartService.updateCart(form);
		return "redirect:/cart";
	}

}
