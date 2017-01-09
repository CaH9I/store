package com.expertsoft.core.service;

import static com.expertsoft.core.util.OrderStates.DELIVERED;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.expertsoft.core.model.DeliveryDao;
import com.expertsoft.core.model.OrderDao;
import com.expertsoft.core.model.ProductDao;
import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.ShoppingCart;

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

    @Test
    public void createOrder() {
        ShoppingCart cart = new ShoppingCart();
        Map<Long, Integer> cartItems = cart.getItems();
        cartItems.put(1L, 1);
        cartItems.put(2L, 2);
        cartItems.put(3L, 3);

        List<MobilePhone> phones = new ArrayList<>();
        MobilePhone phone1 = new MobilePhone(1L, 100);
        MobilePhone phone2 = new MobilePhone(2L, 200);
        MobilePhone phone3 = new MobilePhone(3L, 300);
        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);

        List<CommerceItem> commerceItems = new ArrayList<>();
        commerceItems.add(new CommerceItem(phone1, 1, 100));
        commerceItems.add(new CommerceItem(phone2, 2, 200));
        commerceItems.add(new CommerceItem(phone3, 3, 300));

        when(productDao.findByIds(cartItems.keySet())).thenReturn(phones);
        when(deliveryDao.findFixedDeliveryAmount()).thenReturn(5.0);

        Order order = orderService.createOrder(cart);

        assertEquals(1400, order.getSubtotal(), 0);
        assertEquals(5, order.getDelivery(), 0);
        assertEquals(1405, order.getTotal(), 0);
        assertEquals(commerceItems, order.getCommerceItems());
    }
}
