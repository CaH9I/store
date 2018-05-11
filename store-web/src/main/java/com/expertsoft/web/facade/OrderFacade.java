package com.expertsoft.web.facade;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.model.entity.OrderItem;
import com.expertsoft.core.service.AccountService;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.core.service.ProductService;
import com.expertsoft.core.service.ShoppingCartService;
import com.expertsoft.core.service.component.ShoppingCart;
import com.expertsoft.web.form.OrderForm;
import com.expertsoft.web.security.AclManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.expertsoft.web.util.SecurityUtils.username;

@Component
public class OrderFacade {

    @Value("${delivery.amount}")
    private double deliveryAmount;

    private final OrderService orderService;
    private final ShoppingCartService cartService;
    private final AccountService accountService;
    private final ProductService productService;
    private final AclManager aclManager;

    @Autowired
    public OrderFacade(final OrderService orderService,
                       final ShoppingCartService cartService,
                       final AccountService accountService,
                       final ProductService productService,
                       final AclManager aclManager) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.accountService = accountService;
        this.productService = productService;
        this.aclManager = aclManager;
    }

    @Transactional
    public long placeOrder(OrderForm form) {
        Order order = createOrderFromCart();

        order.setAccount(accountService.findByEmail(username())
                .orElseThrow(RecordNotFoundException::new));
        populateOrder(order, form);

        Order newOrder = orderService.save(order);
        aclManager.addDefaultPermissions(Order.class, newOrder.getId(), newOrder.getAccount().getEmail());

        cartService.clearCart();

        return newOrder.getId();
    }

    public Order createOrderFromCart() {
        Order order = new Order();
        ShoppingCart cart = cartService.getShoppingCart();
        List<MobilePhone> phones = productService.findAllById(cart.getItems().keySet());
        double subtotal = 0;

        for (MobilePhone phone : phones) {
            Integer quantity = cart.getItems().get(phone.getId());
            OrderItem oi = new OrderItem(phone, quantity, phone.getPrice());
            order.addOrderItem(oi);
            subtotal += oi.getPrice() * oi.getQuantity();
        }

        order.setSubtotal(subtotal);
        order.setDelivery(deliveryAmount);
        order.setTotal(subtotal + order.getDelivery());

        return order;
    }

    private void populateOrder(Order order, OrderForm form) {
        order.setFirstName(form.getFirstName());
        order.setLastName(form.getLastName());
        order.setAddress(form.getAddress());
        order.setPhoneNumber(form.getPhoneNumber());
        order.setAdditionalInfo(form.getAdditionalInfo());
    }
}
