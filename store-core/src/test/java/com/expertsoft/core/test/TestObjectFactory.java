package com.expertsoft.core.test;

import com.expertsoft.core.model.entity.Account;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.model.entity.OrderItem;
import com.expertsoft.core.model.entity.Role;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

import static com.expertsoft.core.model.entity.OrderState.DELIVERED;
import static com.expertsoft.core.model.entity.OrderState.SUBMITTED;

public class TestObjectFactory {

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    private static final List<Role> roles = List.of(
            Role.of(1L, ADMIN_ROLE),
            Role.of(2L, USER_ROLE));

    private static final List<Account> accounts;

    static {
        var admin = new Account();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("$2a$04$TeWT/i9Iuht8jAgxMOaKTuHpdaHvrKpVwv9npt13g0BR0H7DPCweW");
        admin.setRoles(Set.of(getAdminRole(), getUserRole()));

        var user = new Account();
        user.setId(2L);
        user.setUsername("user");
        user.setPassword("$2a$04$QP5sIhM6txQCD6B5Ujem1.oub.LaMiS9hu18hFmNYEx1zNebvmmZy");
        user.setRoles(Set.of(getUserRole()));

        accounts = List.of(admin, user);
    }

    private static final List<MobilePhone> phones = List.of(
            new MobilePhone(1L, "Samsung galaxy SII", 600.0),
            new MobilePhone(2L, "Samsung galaxy SIII", 650.0),
            new MobilePhone(3L, "Samsung galaxy S6", 750.0));

    private static final List<Order> orders;

    static {
        var oi11 = new OrderItem();
        oi11.setPhone(getMobilePhone(1L));
        oi11.setPrice(400.0);
        oi11.setQuantity(2);

        var oi12 = new OrderItem();
        oi12.setPhone(getMobilePhone(2L));
        oi12.setPrice(500.0);
        oi12.setQuantity(3);

        var oi13 = new OrderItem();
        oi13.setPhone(getMobilePhone(3L));
        oi13.setPrice(550.0);
        oi13.setQuantity(1);

        var order1 = new Order();
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

        order1.addOrderItem(oi11);
        order1.addOrderItem(oi12);
        order1.addOrderItem(oi13);

        var oi21 = new OrderItem();
        oi21.setPhone(getMobilePhone(2L));
        oi21.setPrice(550.0);
        oi21.setQuantity(4);

        var oi22 = new OrderItem();
        oi22.setPhone(getMobilePhone(3L));
        oi22.setPrice(600.0);
        oi22.setQuantity(2);

        var order2 = new Order();
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

        order2.addOrderItem(oi21);
        order2.addOrderItem(oi22);

        var oi31 = new OrderItem();
        oi31.setPhone(getMobilePhone(3L));
        oi31.setPrice(500.0);
        oi31.setQuantity(8);

        var order3 = new Order();
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

        order3.addOrderItem(oi31);

        orders = List.of(order1, order2, order3);
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
                .filter(order -> order.getAccount().getUsername().equals(username))
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
        var item = new OrderItem();
        item.setPhone(entityManager.merge(getTestMobilePhone()));
        item.setPrice(500.0);
        item.setQuantity(2);

        var order = new Order();
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
                .filter(account -> username.equals(account.getUsername()))
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
