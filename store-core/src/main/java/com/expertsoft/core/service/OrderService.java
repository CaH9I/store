package com.expertsoft.core.service;

import static com.expertsoft.core.util.OrderStates.DELIVERED;

import java.util.List;

import com.expertsoft.core.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.DeliveryDao;
import com.expertsoft.core.model.OrderDao;
import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.ShoppingCart;

@Service
// TODO @Transactional
public class OrderService {

    private final OrderDao orderDao;
    private final DeliveryDao deliveryDao;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderDao orderDao, DeliveryDao deliveryDao, ProductRepository productRepository) {
        this.orderDao = orderDao;
        this.deliveryDao = deliveryDao;
        this.productRepository = productRepository;
    }

    public long saveOrder(Order order) {
        return orderDao.save(order);
    }

    public void changeOrderToDelivered(long orderId) {
        orderDao.updateStateById(orderId, DELIVERED);
    }

    public void populateOrder(Order order, String firstName, String lastName, String address, String phoneNumber, String additionalInfo) {
        order.setFirstName(firstName);
        order.setLastName(lastName);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setAdditionalInfo(additionalInfo);
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public void deleteOrderById(long id) {
        orderDao.deleteById(id);
    }

    public Order getOrderById(long id) {
        return orderDao.findById(id);
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
        order.setDelivery(deliveryDao.findFixedDeliveryAmount());
        order.setTotal(subtotal + order.getDelivery());

        return order;
    }
}
