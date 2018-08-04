package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.test.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.expertsoft.core.model.entity.OrderState.DELIVERED;
import static com.expertsoft.core.test.TestObjectFactory.createTestOrder;
import static com.expertsoft.core.test.TestObjectFactory.getTestOrder;
import static com.expertsoft.core.test.TestObjectFactory.getTestOrders;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class OrderServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void deleteById() {
        var testOrder = getTestOrder();

        orderService.deleteById(testOrder.getId());

        assertFalse(orderRepository.findById(testOrder.getId()).isPresent());
    }

    @Test
    public void save() {
        var order = createTestOrder(entityManager);

        var savedOrder = orderService.save(order);

        assertEquals(order, savedOrder);
    }

    @Test
    public void changeOrderState() {
        var query = entityManager.createQuery(
                "select o from Order o where o.state <> :state", Order.class);
        query.setParameter("state", DELIVERED);
        query.setMaxResults(1);
        var order = query.getSingleResult();

        orderService.changeOrderState(order.getId(), DELIVERED);

        assertEquals(DELIVERED, order.getState());
    }

    @Test
    public void getOrderByIdWithItemsAndProducts() {
        var testOrder = getTestOrder();

        var order = orderService.findById(testOrder.getId());

        assertEquals(testOrder, order);
        assertEquals(testOrder.getOrderItems(), order.getOrderItems());
    }

    @Test(expected = RecordNotFoundException.class)
    public void getOrderByIdWithItemsAndProductsNotExists() {
        orderService.findById(0L);
    }

    @Test
    public void findAll() {
        var orders = orderService.findAll();

        assertThat(orders, containsInAnyOrder(getTestOrders().toArray()));
    }
}
