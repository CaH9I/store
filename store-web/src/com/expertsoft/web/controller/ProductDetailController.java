package com.expertsoft.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.ProductService;
import com.expertsoft.core.service.ShoppingCartService;

@Controller
@RequestMapping("/product-detail")
public class ProductDetailController {
	
	private ProductService productService;
	private ShoppingCartService cartService;
	
	@Autowired
	public ProductDetailController(ProductService productService, ShoppingCartService cartService) {
		this.productService = productService;
		this.cartService = cartService;
	}

	@RequestMapping(value="/{id}", method=GET)
	public String productDetail(@PathVariable long id, Model model) {
		model.addAttribute(productService.getById(id));
		model.addAttribute(cartService.getShoppingCart());
		return "productDetail";
	}
}
