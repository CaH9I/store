package com.expertsoft.web.controller.product;

import com.expertsoft.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductListController {

    private ProductService productService;

    @Autowired
    public ProductListController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productList(Model model) {
        return productListForPage(1, model);
    }

    @GetMapping("/{page}")
    public String productListForPage(@PathVariable int page, Model model) {
        model.addAttribute("mobilePhones", productService.findAll(page - 1));
        return "product/productList";
    }
}
