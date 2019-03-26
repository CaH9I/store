package com.expertsoft.web.controller.cart;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.web.dto.form.UpdateCartForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ShoppingCart cart;

    @Autowired
    public CartController(ShoppingCart cart) {
        this.cart = cart;
    }

    @GetMapping
    public String cart(Model model) {
        model.addAttribute("updateCartForm", UpdateCartForm.from(cart));
        return "cart/cart";
    }

    @DeleteMapping("{productId}")
    public String removeCartItem(@PathVariable long productId) {
        cart.remove(productId);
        return "redirect:/cart";
    }

    @PostMapping
    public String updateCart(@Valid UpdateCartForm form, Errors errors, @RequestParam(required = false) boolean checkout) {
        if (errors.hasErrors()) {
            return "cart/cart";
        }
        cart.update(form.getItems());
        return checkout ? "redirect:/order" : "redirect:/cart";
    }
}
