package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.core.service.component.ShoppingCartView;
import com.expertsoft.web.form.UpdateCartForm;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

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
    private ShoppingCartService shoppingCartService;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void cart() throws Exception {
        mockMvc.perform(get("/cart"))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(model().attribute("updateCartForm", isA(UpdateCartForm.class)))
                .andExpect(view().name("cart"))
                .andExpect(status().isOk());
    }

    @Test
    public void removeFromCart() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        shoppingCartService.addToCart(testPhone.getId(), 10);

        mockMvc.perform(post("/cart")
                .param("productToRemoveId", testPhone.getId().toString())
                .session(session))
                .andExpect(redirectedUrl("/cart"))
                .andExpect(status().is3xxRedirection());

        assertThat(shoppingCartService.getShoppingCart().getItems(), not(hasKey(testPhone.getId())));
    }

    @Test
    public void addToCart() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();

        mockMvc.perform(post("/cart/add-to-cart")
                .param("productId", testPhone.getId().toString())
                .param("quantity", "10")
                .session(session))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(view().name("json/addToCartSuccess"))
                .andExpect(status().isOk());

        assertThat(shoppingCartService.getShoppingCart().getItems(), hasKey(testPhone.getId()));
    }

    @Test
    public void addToCartIncorrectQuantity() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();

        mockMvc.perform(post("/cart/add-to-cart")
                .param("productId", testPhone.getId().toString())
                .param("quantity", "0")
                .session(session))
                .andExpect(model().attributeHasErrors("addToCartForm"))
                .andExpect(model().attribute("errors", isA(Errors.class)))
                .andExpect(view().name("json/addToCartError"))
                .andExpect(status().isBadRequest());

        assertThat(shoppingCartService.getShoppingCart().getItems(), not(hasKey(testPhone.getId())));
    }

    @Test
    public void updateCart() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        shoppingCartService.addToCart(testPhone.getId(), 10);

        mockMvc.perform(post("/cart")
                .param("items[" + testPhone.getId() + "].quantity", "5")
                .session(session))
                .andExpect(redirectedUrl("/cart"))
                .andExpect(status().is3xxRedirection());

        assertThat(shoppingCartService.getShoppingCart().getItems(), hasEntry(testPhone.getId(), 5));
    }

    @Test
    public void updateCartProceedCheckout() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        shoppingCartService.addToCart(testPhone.getId(), 10);

        mockMvc.perform(post("/cart")
                .param("items[" + testPhone.getId() + "].quantity", "5")
                .param("checkout", "true")
                .session(session))
                .andExpect(redirectedUrl("/order"))
                .andExpect(status().is3xxRedirection());

        assertThat(shoppingCartService.getShoppingCart().getItems(), hasEntry(testPhone.getId(), 5));
    }

    @Test
    public void updateCartIncorrectQuantity() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        shoppingCartService.addToCart(testPhone.getId(), 10);

        mockMvc.perform(post("/cart")
                .param("items[" + testPhone.getId() + "].quantity", "0")
                .session(session))
                .andExpect(model().attributeHasErrors("updateCartForm"))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(model().attribute("updateCartForm", isA(UpdateCartForm.class)))
                .andExpect(view().name("cart"))
                .andExpect(status().isOk());

        assertThat(shoppingCartService.getShoppingCart().getItems(), hasEntry(testPhone.getId(), 10));
    }
}
