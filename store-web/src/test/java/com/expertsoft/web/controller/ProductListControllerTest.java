package com.expertsoft.web.controller;

import com.expertsoft.core.commerce.ShoppingCartView;
import com.expertsoft.core.service.ProductService;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class ProductListControllerTest extends WebApplicationTest {

    private static final int PAGE_NUMBER = 1;

    @Autowired
    private ProductService productService;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void productList() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attribute("mobilePhones", productService.findAll(0)))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(view().name("productList"))
                .andExpect(status().isOk());
    }

    @Test
    public void productListForPage() throws Exception {
        mockMvc.perform(get("/" + PAGE_NUMBER))
                .andExpect(model().attribute("mobilePhones", productService.findAll(PAGE_NUMBER - 1)))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(view().name("productList"))
                .andExpect(status().isOk());
    }

    @Test
    public void productListForPageNotFound() throws Exception {
        mockMvc.perform(get("/" + 1000))
                .andExpect(status().isNotFound());
    }
}
