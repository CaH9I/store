package com.expertsoft.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.expertsoft.core.service.OrderService;
import com.expertsoft.core.service.ProductService;
import com.expertsoft.core.service.ShoppingCartService;

@Controller
@RequestMapping({"/", "/product-list"})
public class ProductListController {

    private ProductService productService;
    private ShoppingCartService cartService;
    private OrderService orderService;

    @Autowired
    public ProductListController(ProductService productService, ShoppingCartService cartService, OrderService orderService) {
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public String productList(Model model) {
        model.addAttribute("mobilePhones", productService.getAll());
        model.addAttribute("order", orderService.createOrder(cartService.getShoppingCart()));
        return "productList";
    }
}
