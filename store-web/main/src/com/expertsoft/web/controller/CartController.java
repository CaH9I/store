package com.expertsoft.web.controller;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.expertsoft.core.service.OrderService;
import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.web.form.AddToCartForm;
import com.expertsoft.web.form.UpdateCartForm;
import com.expertsoft.web.util.FormUtil;

@Controller
@RequestMapping("/cart")
public class CartController {

    private ShoppingCartService cartService;
    private OrderService orderService;

    @Autowired
    public CartController(ShoppingCartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public String cart(Model model) {
        model.addAttribute("order", orderService.createOrder(cartService.getShoppingCart()));
        model.addAttribute("updateCartForm", new UpdateCartForm(cartService.getShoppingCart()));
        return "cart";
    }

    @PostMapping(params = "productToRemoveId")
    public String removeFromCart(@RequestParam long productToRemoveId, Model model) {
        cartService.removeFromCart(productToRemoveId);
        return "redirect:/cart";
    }

    @PostMapping
    public String updateCart(@ModelAttribute @Valid UpdateCartForm form, Errors errors, @RequestParam(required = false) boolean checkout, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("order", orderService.createOrder(cartService.getShoppingCart()));
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
        model.addAttribute("order", orderService.createOrder(cartService.getShoppingCart()));
        return "json/addToCartSuccess";
    }
}
