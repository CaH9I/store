package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.model.entity.CommerceItem;
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
public class OrderService {

    private static final Sort ORDER_SORT = new Sort(DESC, "id");

    @Value("${delivery.amount}")
    private double deliveryAmount;

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public Long saveOrder(Order order) {
        return orderRepository.save(order).getId();
    }

    public void changeOrderState(Long orderId, OrderState state) {
        Order order = orderRepository.getOne(orderId);
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
    public List<Order> getAllOrders() {
        return orderRepository.findAll(ORDER_SORT);
    }

    public void deleteOrderById(Long id) {
        orderRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Order getOrderByIdWithItemsAndProducts(Long id) {
        return Optional.ofNullable(orderRepository.findOneWithItemsAndProducts(id))
                .orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(propagation = SUPPORTS)
    public Order createOrder(ShoppingCart cart) {
        Order order = new Order();
        List<MobilePhone> phones = productRepository.findByIdIn(cart.getItems().keySet());
        double subtotal = 0;

        for (MobilePhone phone : phones) {
            Integer quantity = cart.getItems().get(phone.getId());
            CommerceItem ci = new CommerceItem(phone, quantity, phone.getPrice());
            order.addCommerceItem(ci);
            subtotal += ci.getPrice() * ci.getQuantity();
        }

        order.setSubtotal(subtotal);
        order.setDelivery(deliveryAmount);
        order.setTotal(subtotal + order.getDelivery());

        return order;
    }
}
