package com.expertsoft.core.model;

import com.expertsoft.core.model.entity.Order;

public interface JdbcOrderDao {

    void save(Order order);

}
