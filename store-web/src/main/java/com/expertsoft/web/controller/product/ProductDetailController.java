package com.expertsoft.web.controller.product;

import com.expertsoft.core.commerce.ShoppingCartView;
import com.expertsoft.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product-detail/{id}")
public class ProductDetailController {

    private ProductService productService;
    private ShoppingCartView cartView;

    @Autowired
    public ProductDetailController(ProductService productService, ShoppingCartView cartView) {
        this.productService = productService;
        this.cartView = cartView;
    }

    @GetMapping
    public String productDetail(@PathVariable long id, Model model) {
        model.addAttribute("mobilePhone", productService.findById(id));
        model.addAttribute("cartView", cartView);
        return "productDetail";
    }
}
