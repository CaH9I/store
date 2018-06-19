package com.expertsoft.web.controller.cart;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.web.dto.form.UpdateCartForm;
import com.expertsoft.web.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("updateCartForm", UpdateCartForm.of(cart));
        return "cart/cart";
    }

    @PostMapping(params = "productToRemoveId")
    public String removeFromCart(@RequestParam long productToRemoveId) {
        cart.remove(productToRemoveId);
        return "redirect:/cart";
    }

    @PostMapping
    public String updateCart(@Valid UpdateCartForm form, Errors errors, @RequestParam(required = false) boolean checkout) {
        if (errors.hasErrors()) {
            return "cart/cart";
        }
        cart.update(FormUtils.buildItemsMap(form));
        return checkout ? "redirect:/order" : "redirect:/cart";
    }
}
