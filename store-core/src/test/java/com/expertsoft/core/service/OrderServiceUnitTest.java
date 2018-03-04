package com.expertsoft.core.service;

import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

    private static Long orderId = 1L;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Order order;

    @Test
    public void getAllOrders() {
        List<Order> orders = singletonList(order);
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertEquals(orders, result);
    }

    @Test
    public void deleteOrderById() {
        orderService.deleteOrderById(orderId);

        verify(orderRepository).delete(orderId);
    }

    @Test
    public void getOrderById() {
        when(orderRepository.findOneWithItemsAndProducts(orderId)).thenReturn(order);

        Order result = orderService.getOrderByIdWithItemsAndProducts(orderId);

        assertEquals(order, result);
    }

    @Test
    public void saveOrder() {
        Order savedOrder = new Order();
        savedOrder.setId(orderId);
        when(orderRepository.save(order)).thenReturn(savedOrder);

        Long savedOrderId = orderService.saveOrder(order);

        verify(orderRepository).save(order);
        assertEquals(orderId, savedOrderId);
    }

    @Test
    public void changeOrderToDelivered() {
        when(orderRepository.getOne(orderId)).thenReturn(order);

        orderService.changeOrderToDelivered(orderId);

        verify(order).setState(Order.OrderState.DELIVERED);
    }

    @Test
    public void populateOrder() {
        Order order = new Order();
        String firstName = "fName";
        String lastName = "lName";
        String address = "address";
        String phoneNumber = "phoneNumber";
        String additionalInfo = "additionalInfo";

        orderService.populateOrder(order, firstName, lastName, address, phoneNumber, additionalInfo);

        assertEquals(firstName, order.getFirstName());
        assertEquals(lastName, order.getLastName());
        assertEquals(address, order.getAddress());
        assertEquals(phoneNumber, order.getPhoneNumber());
        assertEquals(additionalInfo, order.getAdditionalInfo());
    }

    @Test
    public void createOrder() {
        // given
        ShoppingCart cart = new ShoppingCart();
        Map<Long, Integer> cartItems = cart.getItems();
        cartItems.put(1L, 1);
        cartItems.put(2L, 2);

        List<MobilePhone> phones = new ArrayList<>();
        MobilePhone phone1 = new MobilePhone(1L, "LG", 100.0);
        MobilePhone phone2 = new MobilePhone(2L, "Apple iPhone 5S", 200.0);
        phones.add(phone1);
        phones.add(phone2);

        Order order = new Order();
        order.addCommerceItem(new CommerceItem(phone1, 1, 100.0));
        order.addCommerceItem(new CommerceItem(phone2, 2, 200.0));
        order.setSubtotal(500.0);
        order.setDelivery(0.0);
        order.setTotal(500.0);

        when(productRepository.findByIdIn(cartItems.keySet())).thenReturn(phones);

        // when
        Order result = orderService.createOrder(cart);

        // then
        assertEquals(order, result);
        assertEquals(order.getCommerceItems(), result.getCommerceItems());
    }
}
