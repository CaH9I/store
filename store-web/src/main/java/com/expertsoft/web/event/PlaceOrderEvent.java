package com.expertsoft.web.event;

import com.expertsoft.core.model.entity.Order;
import org.springframework.context.ApplicationEvent;

public class PlaceOrderEvent extends ApplicationEvent {

    public PlaceOrderEvent(Order order) {
        super(order);
    }
}
