package com.expertsoft.web.controller;

import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.test.TestObjectFactory;
import com.expertsoft.web.test.WebApplicationTest;
import com.expertsoft.web.test.WithAdmin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static com.expertsoft.core.model.entity.Order.OrderState.DELIVERED;
import static com.expertsoft.core.model.entity.Order.OrderState.SUBMITTED;
import static com.expertsoft.core.test.TestObjectFactory.getTestOrders;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WithAdmin
public class AdminHomeControllerTest extends WebApplicationTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void admin() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(model().attribute("orders", getTestOrders()))
                .andExpect(view().name("admin/home"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void adminWithNoPermission() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void deleteOrder() throws Exception {
        Order testOrder = TestObjectFactory.getTestOrder();

        mockMvc.perform(post("/admin")
                .with(csrf())
                .param("orderToRemoveId", testOrder.getId().toString()))
                .andExpect(redirectedUrl("/admin"))
                .andExpect(status().is3xxRedirection());

        assertFalse(orderRepository.exists(testOrder.getId()));
    }

    @Test
    @WithMockUser
    public void deleteOrderWithNoPermission() throws Exception {
        mockMvc.perform(post("/admin")
                .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void changeOrderToDelivered() throws Exception {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.state = :state", Order.class);
        query.setParameter("state", SUBMITTED);
        query.setMaxResults(1);
        Order order = query.getSingleResult();

        mockMvc.perform(post("/admin")
                .with(csrf())
                .param("orderToDeliverId", order.getId().toString()))
                .andExpect(redirectedUrl("/admin"))
                .andExpect(status().is3xxRedirection());

        assertEquals(DELIVERED, order.getState());
    }

    @Test
    @WithMockUser
    public void changeOrderToDeliveredWithNoPermission() throws Exception {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.state = :state", Order.class);
        query.setParameter("state", SUBMITTED);
        query.setMaxResults(1);
        Order order = query.getSingleResult();

        mockMvc.perform(post("/admin")
                .with(csrf())
                .param("orderToDeliverId", order.getId().toString()))
                .andExpect(status().isForbidden());

        assertEquals(SUBMITTED, order.getState());
    }
}
