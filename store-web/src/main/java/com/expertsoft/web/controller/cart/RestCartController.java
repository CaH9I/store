package com.expertsoft.web.controller.cart;

import com.expertsoft.web.dto.AddToCartResponse;
import com.expertsoft.web.dto.form.AddToCartForm;
import com.expertsoft.web.facade.CartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

@RestController
@RequestMapping("/ajax/cart")
public class RestCartController {

    private final CartFacade cartFacade;

    @Autowired
    public RestCartController(CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    @PutMapping
    public AddToCartResponse addToCart(@Valid AddToCartForm form, Errors errors, HttpServletResponse resp) {
        if (errors.hasErrors()) {
            resp.setStatus(SC_BAD_REQUEST);
            return cartFacade.addToCartError(errors);
        }
        return cartFacade.addToCartSuccess(form);
    }
}
