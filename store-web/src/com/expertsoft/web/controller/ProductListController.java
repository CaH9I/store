package com.expertsoft.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.StoreService;

@Controller
@RequestMapping(value={"/", "/product-list"})
public class ProductListController {
	
	private StoreService storeService;
	
	@Autowired
	public ProductListController(StoreService storeService) {
		this.storeService = storeService;
	}

	@RequestMapping(method=GET)
	public String productList(Model model) {
		model.addAttribute(storeService.getAll());
		model.addAttribute(storeService.getShoppingCart());
		return "productList";
	}
}
