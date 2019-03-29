package com.expertsoft.web.controller.cart;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.NumberFormat;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class RestCartControllerTest extends WebApplicationTest {

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private NumberFormat priceFormatter;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void addToCart() throws Exception {
        var testPhone = getTestMobilePhone();
        Integer qty = 10;

        mockMvc.perform(put("/ajax/cart")
                .param("productId", testPhone.getId().toString())
                .param("quantity", qty.toString())
                .session(session))
                .andExpect(jsonPath("$.numberOfItems", is(qty)))
                .andExpect(jsonPath("$.subtotal", is(priceFormatter.format(testPhone.getPrice() * qty))))
                .andExpect(jsonPath("$.errors", empty()))
                .andExpect(status().isOk());

        assertThat(cart.getItems(), hasEntry(testPhone.getId(), qty));
    }

    @Test
    public void addToCartIncorrectQuantity() throws Exception {
        var testPhone = getTestMobilePhone();

        mockMvc.perform(put("/ajax/cart")
                .param("productId", testPhone.getId().toString())
                .param("quantity", "0")
                .session(session))
                .andExpect(jsonPath("$.numberOfItems", nullValue()))
                .andExpect(jsonPath("$.subtotal", nullValue()))
                .andExpect(jsonPath("$.errors", not(empty())))
                .andExpect(status().isBadRequest());

        assertThat(cart.getItems().entrySet(), empty());
    }
}
