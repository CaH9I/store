package com.expertsoft.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.StoreService;

@Controller
@RequestMapping("/product-detail")
public class ProductDetailController {
	
	private StoreService storeService;
	
	@Autowired
	public ProductDetailController(StoreService storeService) {
		this.storeService = storeService;
	}

	@RequestMapping(value="/{id}", method=GET)
	public String productDetail(@PathVariable long id, Model model) {
		model.addAttribute(storeService.getById(id));
		model.addAttribute(storeService.getShoppingCart());
		return "productDetail";
	}
}
