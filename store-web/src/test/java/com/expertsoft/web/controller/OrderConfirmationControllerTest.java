package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.Order;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;

import static com.expertsoft.core.test.TestObjectFactory.getTestOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class OrderConfirmationControllerTest extends WebApplicationTest {

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void confirmOrder() throws Exception {
        Order testOrder = getTestOrder();

        mockMvc.perform(get("/order/" + testOrder.getId()))
                .andExpect(model().attribute("order", testOrder))
                .andExpect(view().name("orderConfirm"))
                .andExpect(status().isOk());
    }

    @Test
    public void confirmOrderNotExists() throws Exception {
        mockMvc.perform(get("/order/0"))
                .andExpect(status().isNotFound());
    }
}
