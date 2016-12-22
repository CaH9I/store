package com.expertsoft.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.expertsoft.core.service.ProductService;
import com.expertsoft.core.service.ShoppingCartService;

@Controller
public class ProductController {

    private ProductService productService;
    private ShoppingCartService cartService;

    @Autowired
    public ProductController(ProductService productService, ShoppingCartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping({ "/", "/product-list" })
    public String productList(Model model) {
        model.addAttribute(productService.getAll());
        model.addAttribute(cartService.getShoppingCart());
        return "productList";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(@PathVariable long id, Model model) {
        model.addAttribute(productService.getById(id));
        model.addAttribute(cartService.getShoppingCart());
        return "productDetail";
    }
}
