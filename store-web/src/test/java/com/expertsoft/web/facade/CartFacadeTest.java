package com.expertsoft.web.facade;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.web.dto.AddToCartResponse;
import com.expertsoft.web.dto.form.AddToCartForm;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.NumberFormat;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static org.junit.Assert.assertEquals;

public class CartFacadeTest extends WebApplicationTest {

    private MobilePhone phone;
    private int qty;
    private AddToCartForm form;

    @Autowired
    private CartFacade cartFacade;

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private NumberFormat priceFormatter;

    @Before
    public void init() {
        phone = getTestMobilePhone();
        qty = 1;
        form = new AddToCartForm();
        form.setQuantity(qty);
        form.setProductId(phone.getId());
    }

    @Test
    public void addToCartSuccess() {
        AddToCartResponse response = cartFacade.addToCartSuccess(form);

        assertEquals(qty, cart.getView().getNumberOfItems());
        assertEquals(phone.getPrice() * qty, cart.getView().getSubtotal(), 0);
        assertEquals(AddToCartResponse.of(qty, priceFormatter.format(phone.getPrice() * qty)), response);
    }
}
