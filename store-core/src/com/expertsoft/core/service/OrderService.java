package com.expertsoft.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.JdbcDeliveryDao;
import com.expertsoft.core.model.JdbcOrderDao;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.OrderForm;

@Service
public class OrderService {

    private JdbcOrderDao orderDao;
    private JdbcDeliveryDao deliveryDao;

    @Autowired
    public OrderService(JdbcOrderDao orderDao, JdbcDeliveryDao deliveryDao) {
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
}
