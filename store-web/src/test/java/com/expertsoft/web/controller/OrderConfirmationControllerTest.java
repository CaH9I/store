package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.Order;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static com.expertsoft.core.test.TestObjectFactory.getAdminOrder;
import static com.expertsoft.core.test.TestObjectFactory.getUserOrder;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WithMockUser
public class OrderConfirmationControllerTest extends WebApplicationTest {

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void confirmOrder() throws Exception {
        Order testOrder = getUserOrder();

        mockMvc.perform(get("/order/" + testOrder.getId()))
                .andExpect(model().attribute("order", testOrder))
                .andExpect(view().name("orderConfirm"))
                .andExpect(status().isOk());
    }

    @Test
    public void confirmOrderNoPermission() throws Exception {
        Order testOrder = getAdminOrder();

        mockMvc.perform(get("/order/" + testOrder.getId()))
                .andExpect(status().isForbidden());
    }
}
