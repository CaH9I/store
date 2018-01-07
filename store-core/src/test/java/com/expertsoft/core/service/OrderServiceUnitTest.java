package com.expertsoft.core.service;

import static java.util.Collections.singletonList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.ShoppingCart;

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
        when(orderRepository.save(order)).thenReturn(order);

        orderService.saveOrder(order);

        verify(orderRepository).save(order);
    }

    @Test
    public void changeOrderToDelivered() {
        when(orderRepository.findOne(orderId)).thenReturn(order);

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
        cartItems.put(3L, 3);

        List<MobilePhone> phones = new ArrayList<>();
        MobilePhone phone1 = new MobilePhone(1L, 100.0);
        MobilePhone phone2 = new MobilePhone(2L, 200.0);
        MobilePhone phone3 = new MobilePhone(3L, 300.0);
        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);

        List<CommerceItem> commerceItems = new ArrayList<>();
        commerceItems.add(new CommerceItem(phone1, 1, 100.0));
        commerceItems.add(new CommerceItem(phone2, 2, 200.0));
        commerceItems.add(new CommerceItem(phone3, 3, 300.0));

        when(productRepository.findByIdIn(cartItems.keySet())).thenReturn(phones);

        // when
        Order result = orderService.createOrder(cart);

        // then
        assertEquals(1400, result.getSubtotal(), 0);
        assertEquals(result.getSubtotal() + result.getDelivery(), result.getTotal(), 0);
        assertEquals(commerceItems, result.getCommerceItems());
    }
}
