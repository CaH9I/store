package com.expertsoft.web.controller;

import com.expertsoft.core.model.OrderRepository;
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
import static com.expertsoft.core.test.TestObjectFactory.getTestOrders;
import static java.lang.String.format;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    public void adminHome() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(model().attribute("orders", containsInAnyOrder(getTestOrders().toArray())))
                .andExpect(view().name("admin/home"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void adminHomeWithNoPermission() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void deleteOrder() throws Exception {
        Order testOrder = getTestOrder();

        mockMvc.perform(delete("/admin/delete-order/" + testOrder.getId())
                .with(csrf()))
                .andExpect(redirectedUrl("/admin"))
                .andExpect(status().is3xxRedirection());

        assertFalse(orderRepository.existsById(testOrder.getId()));
    }

    @Test
    @WithMockUser
    public void deleteOrderWithNoPermission() throws Exception {
        Order testOrder = getTestOrder();

        mockMvc.perform(delete("/admin/delete-order/" + testOrder.getId())
                .with(csrf()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void changeOrderState() throws Exception {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.state <> :state", Order.class);
        query.setParameter("state", DELIVERED);
        query.setMaxResults(1);
        Order order = query.getSingleResult();

        mockMvc.perform(put(format("/admin/update-state/%s/%s", order.getId(), DELIVERED))
                .with(csrf()))
                .andExpect(redirectedUrl("/admin"))
                .andExpect(status().is3xxRedirection());

        assertEquals(DELIVERED, order.getState());
    }

    @Test
    @WithMockUser
    public void changeOrderStateWithNoPermission() throws Exception {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.state <> :state", Order.class);
        query.setParameter("state", DELIVERED);
        query.setMaxResults(1);
        Order order = query.getSingleResult();

        mockMvc.perform(put(format("/admin/update-state/%s/%s", order.getId(), DELIVERED))
                .with(csrf()))
                .andExpect(status().isForbidden());

        assertEquals(SUBMITTED, order.getState());
    }
}
