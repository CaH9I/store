package com.expertsoft.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.DeliveryDao;
import com.expertsoft.core.model.OrderDao;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.OrderForm;

@Service
public class OrderService {

    private OrderDao orderDao;
    private DeliveryDao deliveryDao;

    @Autowired
    public OrderService(OrderDao orderDao, DeliveryDao deliveryDao) {
        this.orderDao = orderDao;
        this.deliveryDao = deliveryDao;
    }

    public void saveOrder(Order order) {
        orderDao.save(order);
    }

    public void populateOrder(Order order, OrderForm orderForm) {
        order.setFirstName(orderForm.getFirstName());
        order.setLastName(orderForm.getLastName());
        order.setAddress(orderForm.getAddress());
        order.setPhoneNumber(orderForm.getPhoneNumber());
        order.setAdditionalInfo(orderForm.getAdditionalInfo());
    }

    public void addDeliveryInfo(Order order) {
        double amount = deliveryDao.findFixedDeliveryAmount();
        order.setDelivery(amount);
    }

    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public void deleteOrderById(long id) {
        orderDao.deleteById(id);
    }

    public Order getById(long id) {
        return orderDao.findById(id);
    }
}
