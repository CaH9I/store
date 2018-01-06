package com.expertsoft.core.model.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.expertsoft.core.model.entity.Order;

public class OrderListener {

    @PrePersist
    @PreUpdate
    public void setOrderForItems(final Order order) {
        order.getCommerceItems().forEach(ci -> ci.setOrder(order));
    }
}