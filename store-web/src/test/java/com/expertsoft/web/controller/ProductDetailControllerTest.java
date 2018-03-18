package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.service.component.ShoppingCartView;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class ProductDetailControllerTest extends WebApplicationTest {

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void productDetail() throws Exception {
        MobilePhone testPhone = getTestMobilePhone();
        mockMvc.perform(get("/product-detail/" + testPhone.getId()))
                .andExpect(model().attribute("mobilePhone", testPhone))
                .andExpect(model().attribute("cartView", isA(ShoppingCartView.class)))
                .andExpect(view().name("productDetail"))
                .andExpect(status().isOk());
    }

    @Test
    public void productDetailNotExists() throws Exception {
        mockMvc.perform(get("/product-detail/0"))
                .andExpect(status().isNotFound());
    }
}
