package com.expertsoft.web.controller;

import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.web.form.AddToCartForm;
import com.expertsoft.web.form.UpdateCartForm;
import com.expertsoft.web.util.FormUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ShoppingCartService cartService;

    @Autowired
    public CartController(ShoppingCartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String cart(Model model) {
        model.addAttribute("cartView", cartService.createShoppingCartView());
        model.addAttribute("updateCartForm", new UpdateCartForm(cartService.getShoppingCart()));
        return "cart";
    }

//    @PostMapping(params = "productToRemoveId")
    @DeleteMapping("/{productId}")
    public String removeFromCart(@PathVariable long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @PostMapping
    public String updateCart(@ModelAttribute @Valid UpdateCartForm form, Errors errors, @RequestParam(required = false) boolean checkout, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("cartView", cartService.createShoppingCartView());
            model.addAttribute("updateCartForm", form);
            return "cart";
        }
        cartService.updateCart(FormUtil.buildItemsMap(form));
        return checkout ? "redirect:/order" : "redirect:/cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@Valid AddToCartForm form, Errors errors, Model model, HttpServletResponse response) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            response.setStatus(SC_BAD_REQUEST);
            return "json/addToCartError";
        }
        cartService.addToCart(form.getProductId(), form.getQuantity());
        model.addAttribute("cartView", cartService.createShoppingCartView());
        return "json/addToCartSuccess";
    }
}
