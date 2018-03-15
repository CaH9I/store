package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.test.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.expertsoft.core.model.entity.Order.OrderState.DELIVERED;
import static com.expertsoft.core.model.entity.Order.OrderState.SUBMITTED;
import static com.expertsoft.core.test.TestObjectFactory.createTestOrder;
import static com.expertsoft.core.test.TestObjectFactory.getTestOrder;
import static com.expertsoft.core.test.TestObjectFactory.getTestOrders;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OrderServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void deleteOrderById() {
        Order testOrder = getTestOrder();

        orderService.deleteOrderById(testOrder.getId());

        assertNull(orderRepository.findOne(testOrder.getId()));
    }

    @Test
    public void saveOrder() {
        Order order = createTestOrder();

        Long savedOrderId = orderService.saveOrder(order);

        assertEquals(order, orderRepository.findOne(savedOrderId));
    }

    @Test
    public void changeOrderToDelivered() {
        TypedQuery<Order> query = entityManager.createQuery(
                "select o from Order o where o.state = :state", Order.class);
        query.setParameter("state", SUBMITTED);
        query.setMaxResults(1);
        Order order = query.getSingleResult();

        orderService.changeOrderToDelivered(order.getId());

        assertEquals(DELIVERED, order.getState());
    }

    @Test
    public void getOrderByIdWithItemsAndProducts() {
        Order testOrder = getTestOrder();

        Order order = orderService.getOrderByIdWithItemsAndProducts(testOrder.getId());

        assertEquals(testOrder, order);
        assertEquals(testOrder.getCommerceItems(), order.getCommerceItems());
    }

    @Test(expected = RecordNotFoundException.class)
    public void getOrderByIdWithItemsAndProductsNotExists() {
        orderService.getOrderByIdWithItemsAndProducts(0L);
    }

    @Test
    public void getAllOrders() {
        List<Order> orders = orderService.getAllOrders();

        assertEquals(getTestOrders(), orders);
    }
}
