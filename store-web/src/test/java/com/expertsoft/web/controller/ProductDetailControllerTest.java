package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
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
                .andExpect(model().attributeExists("cartView"))
                .andExpect(view().name("productDetail"))
                .andExpect(status().is(SC_OK));
    }

    @Test
    public void productDetailNotExists() throws Exception {
        mockMvc.perform(get("/product-detail/0"))
                .andExpect(status().is(SC_NOT_FOUND));
    }

    @Test
    public void productDetailIncorrectId() throws Exception {
        mockMvc.perform(get("/product-detail/not-valid-id"))
                .andExpect(status().is(SC_BAD_REQUEST));
    }
}
