package com.expertsoft.core.test;

import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;

import java.util.List;

import static com.expertsoft.core.model.entity.OrderState.DELIVERED;
import static com.expertsoft.core.model.entity.OrderState.SUBMITTED;
import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public class TestObjectFactory {

    private static final List<MobilePhone> phones = unmodifiableList(asList(
            new MobilePhone(1L, "Samsung galaxy SII", 600.0),
            new MobilePhone(2L, "Samsung galaxy SIII", 650.0),
            new MobilePhone(3L, "Samsung galaxy S6", 750.0)));

    private static final List<Order> orders;

    static {
        CommerceItem ci11 = new CommerceItem();
        ci11.setPhone(getMobilePhone(1L));
        ci11.setPrice(400.0);
        ci11.setQuantity(2);

        CommerceItem ci12 = new CommerceItem();
        ci12.setPhone(getMobilePhone(2L));
        ci12.setPrice(500.0);
        ci12.setQuantity(3);

        CommerceItem ci13 = new CommerceItem();
        ci13.setPhone(getMobilePhone(3L));
        ci13.setPrice(550.0);
        ci13.setQuantity(1);

        Order order1 = new Order();
        order1.setId(1L);
        order1.setFirstName("James");
        order1.setLastName("Smith");
        order1.setAddress("135 Evergreen Ave. Wake Forest, NC 27587");
        order1.setPhoneNumber("202-555-0118");
        order1.setSubtotal(2850.0);
        order1.setDelivery(10.0);
        order1.setTotal(2860.0);
        order1.setAdditionalInfo("Do not delay delivery please");
        order1.setState(SUBMITTED);

        order1.addCommerceItem(ci11);
        order1.addCommerceItem(ci12);
        order1.addCommerceItem(ci13);

        CommerceItem ci21 = new CommerceItem();
        ci21.setPhone(getMobilePhone(2L));
        ci21.setPrice(550.0);
        ci21.setQuantity(4);

        CommerceItem ci22 = new CommerceItem();
        ci22.setPhone(getMobilePhone(3L));
        ci22.setPrice(600.0);
        ci22.setQuantity(2);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setFirstName("John");
        order2.setLastName("Johnson");
        order2.setAddress("7716 Wagon St. Kingsport, TN 37660");
        order2.setPhoneNumber("202-555-0137");
        order2.setSubtotal(3400.0);
        order2.setDelivery(15.0);
        order2.setTotal(3415.0);
        order2.setAdditionalInfo("I'd like you to deliver till December 24");
        order2.setState(SUBMITTED);

        order2.addCommerceItem(ci21);
        order2.addCommerceItem(ci22);

        CommerceItem ci31 = new CommerceItem();
        ci31.setPhone(getMobilePhone(3L));
        ci31.setPrice(500.0);
        ci31.setQuantity(8);

        Order order3 = new Order();
        order3.setId(3L);
        order3.setFirstName("Robert");
        order3.setLastName("Williams");
        order3.setAddress("8668 East Snake Hill St. Yuba City, CA 95993");
        order3.setPhoneNumber("+1-202-555-0106");
        order3.setSubtotal(4000.0);
        order3.setDelivery(5.0);
        order3.setTotal(4005.0);
        order3.setState(DELIVERED);

        order3.addCommerceItem(ci31);

        orders = unmodifiableList(asList(order1, order2, order3));
    }

    public static MobilePhone getTestMobilePhone() {
        return phones.stream()
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No test data available"));
    }

    private static MobilePhone getMobilePhone(Long id) {
        return phones.stream()
                .filter(phone -> phone.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static List<MobilePhone> getTestMobilePhones() {
        return phones;
    }

    public static Order getTestOrder() {
        return orders.stream()
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No test data available"));
    }

    public static List<Order> getTestOrders() {
        return orders;
    }

    public static Order createTestOrder() {
        Order order = new Order();
        order.setFirstName("John");
        order.setLastName("Smith");
        order.setPhoneNumber("+375 44 000-00-00");
        order.setDelivery(10.0);
        order.setSubtotal(1000.0);
        order.setTotal(1010.0);
        order.setAddress("Minsk");
        return order;
    }
}
