package com.expertsoft.web.facade;

import com.expertsoft.core.commerce.ShoppingCart;
import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.model.entity.OrderItem;
import com.expertsoft.core.model.entity.OrderState;
import com.expertsoft.core.service.AccountService;
import com.expertsoft.core.service.OrderService;
import com.expertsoft.core.service.ProductService;
import com.expertsoft.web.dto.form.OrderForm;
import com.expertsoft.web.event.PlaceOrderEvent;
import com.expertsoft.web.security.AclManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.expertsoft.web.util.SecurityUtils.username;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Component
@Transactional
public class OrderFacade {

    @Value("${delivery.amount}")
    private double deliveryAmount;

    private final OrderService orderService;
    private final ShoppingCart cart;
    private final AccountService accountService;
    private final ProductService productService;
    private final AclManager aclManager;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderFacade(final OrderService orderService,
                       final ShoppingCart cart,
                       final AccountService accountService,
                       final ProductService productService,
                       final AclManager aclManager,
                       final ApplicationEventPublisher eventPublisher) {
        this.orderService = orderService;
        this.cart = cart;
        this.accountService = accountService;
        this.productService = productService;
        this.aclManager = aclManager;
        this.eventPublisher = eventPublisher;
    }

    public long placeOrder(OrderForm form) {
        var order = createOrderFromCart();

        order.setAccount(accountService.findByUsername(username())
                .orElseThrow(RecordNotFoundException::new));
        populateOrder(order, form);

        var newOrder = orderService.save(order);
        aclManager.addDefaultPermissions(Order.class, newOrder.getId(), newOrder.getAccount().getUsername());

        eventPublisher.publishEvent(new PlaceOrderEvent(newOrder));

        return newOrder.getId();
    }

    public void deleteOrderById(Long orderId) {
        orderService.deleteById(orderId);
        aclManager.deletePermissions(Order.class, orderId);
    }

    public void updateOrderState(Long orderId, OrderState state) {
        var order = orderService.getOne(orderId);
        order.setState(state);
    }

    @Transactional(propagation = SUPPORTS)
    public Order createOrderFromCart() {
        var order = new Order();
        var phones = productService.findAllById(cart.getItems().keySet());
        var subtotal = 0.0;

        for (var phone : phones) {
            var quantity = cart.getItems().get(phone.getId());
            var oi = new OrderItem(phone, quantity, phone.getPrice());
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
