package com.expertsoft.core.service;

import static com.expertsoft.core.model.entity.Order.OrderState.DELIVERED;

import java.util.List;

import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.model.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.ShoppingCart;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Value("${delivery.amount}")
    private double deliveryAmount;

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Long saveOrder(Order order) {
        return orderRepository.save(order).getId();
    }

    @Transactional
    public void changeOrderToDelivered(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.setState(DELIVERED);
    }

    public void populateOrder(Order order, String firstName, String lastName, String address, String phoneNumber, String additionalInfo) {
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setAdditionalInfo(additionalInfo);
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void deleteOrderById(Long id) {
        orderRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findOneWithItemsAndProducts(id);
    }

    public Order createOrder(ShoppingCart cart) {
        Order order = new Order();
        List<MobilePhone> phones = productRepository.findByIdIn(cart.getItems().keySet());
        double subtotal = 0;

        for (MobilePhone phone : phones) {
            Integer quantity = cart.getItems().get(phone.getId());
            CommerceItem ci = new CommerceItem(phone, quantity, phone.getPrice());
            order.getCommerceItems().add(ci);
            subtotal += ci.getPrice() * ci.getQuantity();
        }

        order.setSubtotal(subtotal);
        order.setDelivery(deliveryAmount);
        order.setTotal(subtotal + order.getDelivery());

        return order;
    }
}
