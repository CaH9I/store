package com.expertsoft.web.controller;

import com.expertsoft.core.commerce.ShoppingCartView;
import com.expertsoft.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductListController {

    private ProductService productService;
    private ShoppingCartView cartView;

    @Autowired
    public ProductListController(ProductService productService, ShoppingCartView cartView) {
        this.productService = productService;
        this.cartView = cartView;
    }

    @GetMapping
    public String productList(Model model) {
        model.addAttribute("mobilePhones", productService.findAll());
        model.addAttribute("cartView", cartView);
        return "productList";
    }
}
