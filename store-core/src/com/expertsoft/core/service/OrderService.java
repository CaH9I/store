package com.expertsoft.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.DeliveryDao;
import com.expertsoft.core.model.OrderDao;
import com.expertsoft.core.model.ProductDao;
import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.service.component.OrderForm;
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

    public Order createOrder(ShoppingCart cart) {
        Order order = new Order();
        List<MobilePhone> phones = productDao.findByIds(cart.getItems().keySet());

        cart.getItems().entrySet().forEach(entry -> {
            MobilePhone phone = phones.stream().filter(ph -> ph.getId() == entry.getKey().longValue()).findFirst().get();
            int quantity = entry.getValue();
            CommerceItem ci = new CommerceItem(phone, quantity, phone.getPrice());
            order.getCommerceItems().add(ci);
        });

        return order;
    }
}
