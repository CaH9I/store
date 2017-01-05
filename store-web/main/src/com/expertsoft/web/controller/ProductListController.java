package com.expertsoft.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.ProductService;
import com.expertsoft.core.service.ShoppingCartService;

@Controller
@RequestMapping({"/", "/product-list"})
public class ProductListController {

    private ProductService productService;
    private ShoppingCartService cartService;

    @Autowired
    public ProductListController(ProductService productService, ShoppingCartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping
    public String productList(Model model) {
        model.addAttribute("mobilePhones", productService.getAll());
        model.addAttribute("cartView", cartService.createShoppingCartView());
        return "productList";
    }
}
