package com.expertsoft.core.service;

import com.expertsoft.core.model.entity.Account;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.model.entity.OrderItem;
import com.expertsoft.core.service.component.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.expertsoft.core.test.TestObjectFactory.getTestAccount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @Mock
    private AccountService accountService;

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
        ShoppingCart cart = createShoppingCart();
        Order order = prepareOrder(cart.getItems());

        Order result = orderService.createOrder(cart);

        assertEquals(order, result);
        assertEquals(order.getOrderItems(), result.getOrderItems());
        assertNull(order.getAccount());
    }

    @Test
    public void createOrderWithAccount() {
        // given
        ShoppingCart cart = createShoppingCart();
        Order order = prepareOrder(cart.getItems());
        Account account = getTestAccount();

        when(accountService.findByEmail(anyString())).thenReturn(Optional.of(account));

        // when
        Order result = orderService.createOrder(cart, account.getEmail());

        // then
        assertEquals(order, result);
        assertEquals(order.getOrderItems(), result.getOrderItems());
        assertEquals(account, result.getAccount());
    }

    private ShoppingCart createShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        Map<Long, Integer> cartItems = cart.getItems();
        cartItems.put(1L, 1);
        cartItems.put(2L, 2);
        return cart;
    }

    private Order prepareOrder(Map<Long, Integer> cartItems) {
        List<MobilePhone> phones = new ArrayList<>();
        MobilePhone phone1 = new MobilePhone(1L, "LG", 100.0);
        MobilePhone phone2 = new MobilePhone(2L, "Apple iPhone 5S", 200.0);
        phones.add(phone1);
        phones.add(phone2);

        Order order = new Order();
        order.addOrderItem(new OrderItem(phone1, 1, 100.0));
        order.addOrderItem(new OrderItem(phone2, 2, 200.0));
        order.setSubtotal(500.0);
        order.setDelivery(0.0);
        order.setTotal(500.0);

        when(productService.findAllById(cartItems.keySet())).thenReturn(phones);

        return order;
    }
}
