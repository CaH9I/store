package com.expertsoft.web.controller;

import com.expertsoft.core.commerce.ShoppingCartView;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhones;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class ProductListControllerTest extends WebApplicationTest {

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void productList() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attribute("mobilePhones", getTestMobilePhones()))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(view().name("productList"))
                .andExpect(status().isOk());
    }
}
