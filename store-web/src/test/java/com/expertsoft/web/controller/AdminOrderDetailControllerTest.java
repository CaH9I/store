package com.expertsoft.web.controller;

import com.expertsoft.core.model.entity.Order;
import com.expertsoft.web.test.WebApplicationTest;
import com.expertsoft.web.test.WithAdmin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static com.expertsoft.core.model.entity.OrderState.DELIVERED;
import static com.expertsoft.core.model.entity.OrderState.SUBMITTED;
import static com.expertsoft.core.test.TestObjectFactory.getTestOrder;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WithAdmin
public class AdminOrderDetailControllerTest extends WebApplicationTest {

    @Autowired
    private EntityManager entityManager;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void orderDetail() throws Exception {
        Order testOrder = getTestOrder();

        mockMvc.perform(get("/admin/order-detail/" + testOrder.getId()))
                .andExpect(model().attribute("order", testOrder))
                .andExpect(view().name("admin/orderDetail"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void orderDetailWithNoPermission() throws Exception {
        Order testOrder = getTestOrder();

        mockMvc.perform(get("/admin/order-detail/" + testOrder.getId()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void changeOrderStare() throws Exception {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.state <> :state", Order.class);
        query.setParameter("state", DELIVERED);
        query.setMaxResults(1);
        Order order = query.getSingleResult();

        mockMvc.perform(put(format("/admin/order-detail/%s/%s", order.getId(), DELIVERED))
                .with(csrf()))
                .andExpect(redirectedUrl("/admin/order-detail/" + order.getId()))
                .andExpect(status().is3xxRedirection());

        assertEquals(DELIVERED, order.getState());
    }

    @Test
    @WithMockUser
    public void changeOrderToDeliveredWithNoPermission() throws Exception {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.state <> :state", Order.class);
        query.setParameter("state", DELIVERED);
        query.setMaxResults(1);
        Order order = query.getSingleResult();

        mockMvc.perform(put(format("/admin/order-detail/%s/%s", order.getId(), DELIVERED))
                .with(csrf()))
                .andExpect(status().isForbidden());

        assertEquals(SUBMITTED, order.getState());
    }
}
