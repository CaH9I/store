package com.expertsoft.web.controller.order;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.web.dto.form.OrderForm;
import com.expertsoft.web.facade.OrderFacade;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static com.expertsoft.web.controller.LoginPageController.LOGIN_URL;
import static com.expertsoft.web.test.TestUtils.checkDefaultPermission;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@WithMockUser
public class OrderControllerTest extends WebApplicationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private MutableAclService aclService;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void order() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(model().attribute("order", isA(Order.class)))
                .andExpect(model().attribute("orderForm", isA(OrderForm.class)))
                .andExpect(view().name("order/order"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void orderAnonymousUser() throws Exception {
        mockMvc.perform(get("/order"))
                .andExpect(redirectedUrlPattern("**" + LOGIN_URL));
    }

    @Test
    @Transactional(propagation = NOT_SUPPORTED)
    public void placeOrder() throws Exception {
        // given
        var testPhone = getTestMobilePhone();
        cart.add(testPhone.getId(), 10);

        // when
        var mvcResult = mockMvc.perform(post("/order").with(csrf())
                .param("firstName", "John")
                .param("lastName", "Smith")
                .param("address", "Minsk")
                .param("phoneNumber", "+375 29 000-00-00")
                .session(session))
                .andExpect(redirectedUrlPattern("/order/{spring:[1-9]+\\d*}"))
                .andReturn();

        //then
        assertEquals(0, cart.getItems().size());

        var location = mvcResult.getResponse().getHeader("Location");
        assertNotNull(location);
        assertTrue(location.matches("/order/[1-9]+\\d*"));

        var orderId = Long.valueOf(location.replaceAll(".*/", ""));
        assertTrue(orderRepository.existsById(orderId));

        var acl = aclService.readAclById(new ObjectIdentityImpl(Order.class, orderId));
        checkDefaultPermission(acl, "user");

        orderFacade.deleteOrderById(orderId);
    }

    @Test
    public void placeOrderError() throws Exception {
        mockMvc.perform(post("/order").with(csrf()))
                .andExpect(model().attributeHasErrors("orderForm"))
                .andExpect(model().attribute("order", isA(Order.class)))
                .andExpect(model().attribute("orderForm", isA(OrderForm.class)))
                .andExpect(view().name("order/order"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void placeOrderAnonymousUser() throws Exception {
        mockMvc.perform(post("/order").with(csrf()))
                .andExpect(redirectedUrlPattern("**" + LOGIN_URL));
    }
}
