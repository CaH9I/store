package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.test.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
