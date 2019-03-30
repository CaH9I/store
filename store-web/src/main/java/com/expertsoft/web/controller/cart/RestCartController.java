package com.expertsoft.web.controller.cart;

import com.expertsoft.web.dto.AddToCartResponse;
import com.expertsoft.web.dto.form.AddToCartForm;
import com.expertsoft.web.facade.CartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/ajax/cart")
public class RestCartController {

    private final CartFacade cartFacade;

    @Autowired
    public RestCartController(CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    @PostMapping
    public ResponseEntity<AddToCartResponse> addToCart(@Valid AddToCartForm form, Errors errors) {
        if (errors.hasErrors()) {
            return badRequest().body(cartFacade.addToCartError(errors));
        }
        return ok().body(cartFacade.addToCartSuccess(form));
    }
}
