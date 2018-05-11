package com.expertsoft.core.test;

import com.expertsoft.core.model.entity.Account;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.model.entity.OrderItem;
import com.expertsoft.core.model.entity.Role;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;

import static com.expertsoft.core.model.entity.OrderState.DELIVERED;
import static com.expertsoft.core.model.entity.OrderState.SUBMITTED;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.unmodifiableList;

public class TestObjectFactory {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    private static final List<Role> roles = unmodifiableList(asList(
            Role.of(1L, ADMIN_ROLE),
            Role.of(2L, USER_ROLE)));

    private static final List<Account> accounts;

    static {
        Account admin = new Account();
        admin.setId(1L);
        admin.setEmail("admin");
        admin.setPassword("$2a$04$TeWT/i9Iuht8jAgxMOaKTuHpdaHvrKpVwv9npt13g0BR0H7DPCweW");
        admin.setRoles(new HashSet<>(asList(getAdminRole(), getUserRole())));

        Account user = new Account();
        user.setId(2L);
        user.setEmail("user");
        user.setPassword("$2a$04$QP5sIhM6txQCD6B5Ujem1.oub.LaMiS9hu18hFmNYEx1zNebvmmZy");
        user.setRoles(singleton(getUserRole()));

        accounts = unmodifiableList(asList(admin, user));
    }

    private static final List<MobilePhone> phones = unmodifiableList(asList(
            new MobilePhone(1L, "Samsung galaxy SII", 600.0),
            new MobilePhone(2L, "Samsung galaxy SIII", 650.0),
            new MobilePhone(3L, "Samsung galaxy S6", 750.0)));

    private static final List<Order> orders;

    static {
        OrderItem ci11 = new OrderItem();
        ci11.setPhone(getMobilePhone(1L));
        ci11.setPrice(400.0);
        ci11.setQuantity(2);

        OrderItem ci12 = new OrderItem();
        ci12.setPhone(getMobilePhone(2L));
        ci12.setPrice(500.0);
        ci12.setQuantity(3);

        OrderItem ci13 = new OrderItem();
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
        order1.setAccount(getUserAccount());

        order1.addOrderItem(ci11);
        order1.addOrderItem(ci12);
        order1.addOrderItem(ci13);

        OrderItem ci21 = new OrderItem();
        ci21.setPhone(getMobilePhone(2L));
        ci21.setPrice(550.0);
        ci21.setQuantity(4);

        OrderItem ci22 = new OrderItem();
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
        order2.setAccount(getAdminAccount());

        order2.addOrderItem(ci21);
        order2.addOrderItem(ci22);

        OrderItem ci31 = new OrderItem();
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
        order3.setAccount(getUserAccount());

        order3.addOrderItem(ci31);

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

    private static Order getOrder(String username) {
        return orders.stream()
                .filter(order -> order.getAccount().getEmail().equals(username))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No test data available"));
    }

    public static Order getAdminOrder() {
        return getOrder("admin");
    }

    public static Order getUserOrder() {
        return getOrder("user");
    }

    public static Order createTestOrder(final EntityManager entityManager) {
        OrderItem item = new OrderItem();
        item.setPhone(entityManager.merge(getTestMobilePhone()));
        item.setPrice(500.0);
        item.setQuantity(2);

        Order order = new Order();
        order.setFirstName("John");
        order.setLastName("Smith");
        order.setPhoneNumber("+375 44 000-00-00");
        order.setDelivery(10.0);
        order.setSubtotal(1000.0);
        order.setTotal(1010.0);
        order.setAddress("Minsk");
        order.addOrderItem(item);
        order.setAccount(getTestAccount());
        return order;
    }

    private static Role getAdminRole() {
        return roleByName(ADMIN_ROLE);
    }

    private static Role getUserRole() {
        return roleByName(USER_ROLE);
    }

    private static Role roleByName(String name) {
        return roles.stream()
                .filter(role -> name.equals(role.getName()))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No test data available"));
    }

    public static Account getTestAccount() {
        return accounts.stream()
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No test data available"));
    }

    private static Account getAccount(String username) {
        return accounts.stream()
                .filter(account -> username.equals(account.getEmail()))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("No test data available"));
    }

    public static Account getUserAccount() {
        return getAccount("user");
    }

    private static Account getAdminAccount() {
        return getAccount("admin");
    }
}
