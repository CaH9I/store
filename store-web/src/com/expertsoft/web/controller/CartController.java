package com.expertsoft.web.controller;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.core.service.component.AddToCartForm;
import com.expertsoft.core.service.component.UpdateCartForm;
import com.expertsoft.core.validator.UpdateCartValidator;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ShoppingCartService cartService;
    private UpdateCartValidator updateCartValidator;

    @Autowired
    public CartController(ShoppingCartService cartService, UpdateCartValidator updateCartValidator) {
        this.cartService = cartService;
        this.updateCartValidator = updateCartValidator;
    }

    @InitBinder("updateCartForm")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(updateCartValidator);
    }

    @GetMapping
    public String cart(Model model) {
        model.addAttribute(cartService.getShoppingCart());
        model.addAttribute(new UpdateCartForm(cartService.getShoppingCart()));
        return "cart";
    }

    @PostMapping(params = "productToRemoveId")
    public String removeFromCart(@RequestParam long productToRemoveId, Model model) {
        cartService.removeFromCart(productToRemoveId);
        return "redirect:/cart";
    }

    @PostMapping
    public String updateCart(@ModelAttribute @Valid UpdateCartForm form, Errors errors,
            @RequestParam(required = false) boolean checkout, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute(cartService.getShoppingCart());
            model.addAttribute(form);
            return "cart";
        }
        cartService.updateCart(form);
        return checkout ? "redirect:/order" : "redirect:/cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@Valid AddToCartForm form, Errors errors, Model model, HttpServletResponse response) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            response.setStatus(SC_BAD_REQUEST);
            return "json/addToCartError";
        }
        cartService.addToCart(form);
        model.addAttribute(cartService.getShoppingCart());
        return "json/addToCartSuccess";
    }
}
