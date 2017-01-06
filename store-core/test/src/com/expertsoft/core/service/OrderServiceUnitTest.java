package com.expertsoft.core.service;

import static com.expertsoft.core.util.OrderStates.DELIVERED;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expertsoft.core.model.DeliveryDao;
import com.expertsoft.core.model.OrderDao;
import com.expertsoft.core.model.ProductDao;
import com.expertsoft.core.model.entity.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderDao orderDao;

    @Mock
    private DeliveryDao deliveryDao;

    @Mock
    private ProductDao productDao;

    @Test
    public void getAllOrders() {
        orderService.getAllOrders();
        verify(orderDao).findAll();
    }

    @Test
    public void deleteOrderById() {
        orderService.deleteOrderById(1L);
        verify(orderDao).deleteById(1L);
    }

    @Test
    public void getOrderById() {
        orderService.getOrderById(1L);
        verify(orderDao).findById(1L);
    }

    @Test
    public void saveOrder() {
        Order order = new Order();
        orderService.saveOrder(order);
        verify(orderDao).save(order);
    }

    @Test
    public void changeOrderToDelivered() {
        orderService.changeOrderToDelivered(1L);
        verify(orderDao).updateStateById(1L, DELIVERED);
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
}
