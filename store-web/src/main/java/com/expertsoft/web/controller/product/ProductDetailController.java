package com.expertsoft.web.controller.product;

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

    @Autowired
    public ProductDetailController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productDetail(@PathVariable long id, Model model) {
        model.addAttribute("mobilePhone", productService.findById(id));
        return "product/productDetail";
    }
}
