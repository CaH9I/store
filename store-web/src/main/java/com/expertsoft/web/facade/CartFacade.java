package com.expertsoft.web.facade;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.web.dto.AddToCartResponse;
import com.expertsoft.web.dto.form.AddToCartForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.text.NumberFormat;

import static java.util.stream.Collectors.toList;

@Component
public class CartFacade {

    private final ShoppingCart cart;
    private final MessageSourceAccessor messageSourceAccessor;
    private final NumberFormat priceFormatter;

    @Autowired
    public CartFacade(ShoppingCart cart, MessageSourceAccessor messageSourceAccessor, NumberFormat priceFormatter) {
        this.cart = cart;
        this.messageSourceAccessor = messageSourceAccessor;
        this.priceFormatter = priceFormatter;
    }

    public AddToCartResponse addToCartSuccess(AddToCartForm form) {
        cart.add(form.getProductId(), form.getQuantity());
        return AddToCartResponse.of(cart.getView().getNumberOfItems(), priceFormatter.format(cart.getView().getSubtotal()));
    }

    public AddToCartResponse addToCartError(Errors errors) {
        return AddToCartResponse.of(errors.getAllErrors()
                .stream()
                .map(messageSourceAccessor::getMessage)
                .collect(toList()));
    }
}
