package com.expertsoft.web.controller.cart;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.core.commerce.ShoppingCartView;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.web.dto.form.UpdateCartForm;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class CartControllerTest extends WebApplicationTest {

    @Autowired
    private ShoppingCart cart;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void cart() throws Exception {
        mockMvc.perform(get("/cart"))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(model().attribute("updateCartForm", isA(UpdateCartForm.class)))
                .andExpect(view().name("cart/cart"))
                .andExpect(status().isOk());
    }

    @Test
    public void removeFromCart() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        cart.add(testPhone.getId(), 10);

        mockMvc.perform(post("/cart")
                .param("productToRemoveId", testPhone.getId().toString())
                .session(session))
                .andExpect(redirectedUrl("/cart"))
                .andExpect(status().is3xxRedirection());

        assertThat(cart.getItems(), not(hasKey(testPhone.getId())));
    }

    @Test
    public void updateCart() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        cart.add(testPhone.getId(), 10);

        mockMvc.perform(post("/cart")
                .param("items[" + testPhone.getId() + "].quantity", "5")
                .session(session))
                .andExpect(redirectedUrl("/cart"))
                .andExpect(status().is3xxRedirection());

        assertThat(cart.getItems(), hasEntry(testPhone.getId(), 5));
    }

    @Test
    public void updateCartProceedCheckout() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        cart.add(testPhone.getId(), 10);

        mockMvc.perform(post("/cart")
                .param("items[" + testPhone.getId() + "].quantity", "5")
                .param("checkout", "true")
                .session(session))
                .andExpect(redirectedUrl("/order"))
                .andExpect(status().is3xxRedirection());

        assertThat(cart.getItems(), hasEntry(testPhone.getId(), 5));
    }

    @Test
    public void updateCartIncorrectQuantity() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        cart.add(testPhone.getId(), 10);

        mockMvc.perform(post("/cart")
                .param("items[" + testPhone.getId() + "].quantity", "0")
                .session(session))
                .andExpect(model().attributeHasErrors("updateCartForm"))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(model().attribute("updateCartForm", isA(UpdateCartForm.class)))
                .andExpect(view().name("cart/cart"))
                .andExpect(status().isOk());

        assertThat(cart.getItems(), hasEntry(testPhone.getId(), 10));
    }
}
