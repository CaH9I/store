package com.expertsoft.web.facade;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.model.entity.OrderItem;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.web.dto.form.OrderForm;
import com.expertsoft.web.test.WebApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.test.context.support.WithMockUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.expertsoft.core.model.entity.OrderState.DELIVERED;
import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static com.expertsoft.core.test.TestObjectFactory.getTestOrder;
import static com.expertsoft.core.test.TestObjectFactory.getUserAccount;
import static com.expertsoft.web.test.TestUtils.checkDefaultPermission;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class OrderFacadeTest extends WebApplicationTest {

    private static final MobilePhone PHONE = getTestMobilePhone();
    private static final int QTY = 1;

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private MutableAclService aclService;

    @WithMockUser
    @Test
    public void placeOrder() {
        // given
        cart.add(PHONE.getId(), QTY);
        var order = orderFacade.createOrderFromCart();
        populateOrder(order);

        // when
        long orderId = orderFacade.placeOrder(createOrderForm(order));

        // then
        var placedOrder = orderService.findById(orderId);
        assertEquals(order, placedOrder);
        assertEquals(placedOrder.getAccount(), getUserAccount());

        var acl = aclService.readAclById(new ObjectIdentityImpl(Order.class, orderId));
        checkDefaultPermission(acl, placedOrder.getAccount().getUsername());
    }

    @Test(expected = NotFoundException.class)
    public void deleteOrderById() {
        var testOrderId = getTestOrder().getId();

        orderFacade.deleteOrderById(testOrderId);

        assertFalse(orderRepository.findById(testOrderId).isPresent());
        aclService.readAclById(new ObjectIdentityImpl(Order.class, testOrderId));
    }

    @Test
    public void updateOrderState() {
        var query = entityManager.createQuery(
                "select o from Order o where o.state <> :state", Order.class);
        query.setParameter("state", DELIVERED);
        query.setMaxResults(1);
        var order = query.getSingleResult();

        orderFacade.updateOrderState(order.getId(), DELIVERED);

        assertEquals(DELIVERED, order.getState());
    }

    @Test
    public void createOrderFromCart() {
        cart.add(PHONE.getId(), QTY);
        var order = orderFacade.createOrderFromCart();

        assertEquals(PHONE.getPrice(), order.getSubtotal());
        assertEquals(PHONE.getPrice() + order.getDelivery(), order.getTotal(), 0);
        assertEquals(1, order.getOrderItems().size());
        assertThat(order.getOrderItems(), contains(createOrderItem(order, PHONE, QTY)));
    }

    private void populateOrder(Order order) {
        order.setFirstName("John");
        order.setLastName("Smith");
        order.setAddress("Belarus");
        order.setPhoneNumber("+375 44 000-00-00");
    }

    private OrderItem createOrderItem(Order order, MobilePhone phone, int qty) {
        var orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setPhone(phone);
        orderItem.setQuantity(qty);
        orderItem.setPrice(phone.getPrice());
        return orderItem;
    }

    private OrderForm createOrderForm(Order order) {
        var form = new OrderForm();
        form.setFirstName(order.getFirstName());
        form.setLastName(order.getLastName());
        form.setAddress(order.getAddress());
        form.setPhoneNumber(order.getPhoneNumber());
        form.setAdditionalInfo(order.getAdditionalInfo());
        return form;
    }
}
