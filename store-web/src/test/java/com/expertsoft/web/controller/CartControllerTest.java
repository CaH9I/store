package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.core.service.component.ShoppingCartView;
import com.expertsoft.web.form.UpdateCartForm;
import com.expertsoft.web.test.WebApplicationTest;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Ignore
    @Test
    public void removeFromCart() throws Exception {
        MockHttpSession session = new MockHttpSession();
        MobilePhone testPhone = getTestMobilePhone();
        shoppingCartService.addToCart(testPhone.getId(), 10);

        mockMvc.perform(delete("/cart/" + testPhone.getId()).session(session))
                .andExpect(redirectedUrl("/cart"));

        mockMvc.perform(get("/cart").session(session))
                .andExpect(model().attribute("cartView", new TypeSafeMatcher<ShoppingCartView>() {
                    @Override
                    public boolean matchesSafely(ShoppingCartView item) {
                        return !item.getItems().containsKey(testPhone.getId());
                    }

                    @Override
                    public void describeTo(Description description) {}
                }));

        //assertThat(shoppingCartService.getShoppingCart().getItems(), not(hasKey(testPhone.getId())));
    }
}
