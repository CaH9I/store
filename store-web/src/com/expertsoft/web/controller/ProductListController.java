package com.expertsoft.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.ProductService;
import com.expertsoft.core.service.ShoppingCartService;

@Controller
@RequestMapping(value={"/", "/product-list"})
public class ProductListController {
	
	private ProductService productService;
	private ShoppingCartService cartService;
	
	@Autowired
	public ProductListController(ProductService productService, ShoppingCartService cartService) {
		this.productService = productService;
		this.cartService = cartService;
	}

	@RequestMapping(method=GET)
	public String productList(Model model) {
		model.addAttribute(productService.getAll());
		model.addAttribute(cartService.getShoppingCart());
		return "productList";
	}
}
