package com.expertsoft.core.service;

import static com.expertsoft.core.util.OrderStates.DELIVERED;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.DeliveryDao;
import com.expertsoft.core.model.OrderDao;
import com.expertsoft.core.model.ProductDao;
import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.ShoppingCart;

@Service
public class OrderService {

    private OrderDao orderDao;
    private DeliveryDao deliveryDao;
    private ProductDao productDao;

    @Autowired
    public OrderService(OrderDao orderDao, DeliveryDao deliveryDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.deliveryDao = deliveryDao;
        this.productDao = productDao;
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
        List<MobilePhone> phones = productDao.findByIds(cart.getItems().keySet());
        double subtotal = 0;

        for (Map.Entry<Long, Integer> entry : cart.getItems().entrySet()) {
            MobilePhone phone = phones.stream()
                    .filter(ph -> ph.getId() == entry.getKey().longValue())
                    .findFirst().get();
            int quantity = entry.getValue();
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
