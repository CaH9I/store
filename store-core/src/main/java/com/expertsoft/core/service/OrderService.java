package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.OrderItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.model.entity.OrderState;
import com.expertsoft.core.service.component.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
@Transactional
public class OrderService extends RepositoryService<Order, Long, OrderRepository> {

    private static final Sort ORDER_SORT = new Sort(DESC, "id");

    @Value("${delivery.amount}")
    private double deliveryAmount;

    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService) {
        super(orderRepository, Order.class);
        this.productService = productService;
    }

    public Long save(Order order) {
        return repository.save(order).getId();
    }

    public void changeOrderState(Long orderId, OrderState state) {
        Order order = repository.getOne(orderId);
        order.setState(state);
    }

    @Transactional(propagation = SUPPORTS)
    public void populateOrder(Order order, String firstName, String lastName, String address, String phoneNumber, String additionalInfo) {
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setAdditionalInfo(additionalInfo);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return repository.findAll(ORDER_SORT);
    }

    @Transactional(readOnly = true)
    public Order getOrderByIdWithItemsAndProducts(Long id) {
        return Optional.ofNullable(repository.findOneWithItemsAndProducts(id))
                .orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(propagation = SUPPORTS)
    public Order createOrder(ShoppingCart cart) {
        Order order = new Order();
        List<MobilePhone> phones = productService.findAllById(cart.getItems().keySet());
        double subtotal = 0;

        for (MobilePhone phone : phones) {
            Integer quantity = cart.getItems().get(phone.getId());
            OrderItem ci = new OrderItem(phone, quantity, phone.getPrice());
            order.addOrderItem(ci);
            subtotal += ci.getPrice() * ci.getQuantity();
        }

        order.setSubtotal(subtotal);
        order.setDelivery(deliveryAmount);
        order.setTotal(subtotal + order.getDelivery());

        return order;
    }
}
