package com.expertsoft.core.model;

import java.util.List;

import com.expertsoft.core.model.entity.Order;

public interface OrderDao {

    long save(Order order);

    List<Order> findAll();

    Order findById(long orderId);

    void deleteById(long orderId);

}
