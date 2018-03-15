package com.expertsoft.web.controller;

import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.web.form.OrderForm;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

public class OrderControllerTest extends WebApplicationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private MockHttpSession session;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    public void order() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(model().attribute("order", isA(Order.class)))
                .andExpect(model().attribute("orderForm", isA(OrderForm.class)))
                .andExpect(view().name("order"))
                .andExpect(status().isOk());
    }

    @Test
    public void placeOrder() throws Exception {
        // given
        MobilePhone testPhone = getTestMobilePhone();
        shoppingCartService.addToCart(testPhone.getId(), 10);

        // when
        MvcResult result = mockMvc.perform(post("/order")
                .param("firstName", "John")
                .param("lastName", "Smith")
                .param("address", "Minsk")
                .param("phoneNumber", "+375 29 000-00-00")
                .session(session))
                .andExpect(redirectedUrlPattern("/order/{spring:[1-9]+\\d*}"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        //then
        assertEquals(0, shoppingCartService.getShoppingCart().getItems().size());

        String location = result.getResponse().getHeader("Location");
        assertNotNull(location);
        assertTrue(location.matches("/order/[1-9]+\\d*"));

        Long orderId = Long.valueOf(location.replaceAll(".*/", ""));
        assertTrue(orderRepository.exists(orderId));
    }

    @Test
    public void placeOrderError() throws Exception {
        mockMvc.perform(post("/order"))
                .andExpect(model().attributeHasErrors("orderForm"))
                .andExpect(model().attribute("order", isA(Order.class)))
                .andExpect(model().attribute("orderForm", isA(OrderForm.class)))
                .andExpect(view().name("order"))
                .andExpect(status().isOk());
    }
}
