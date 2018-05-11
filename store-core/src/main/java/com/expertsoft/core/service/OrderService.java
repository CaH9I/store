package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.Order;
import com.expertsoft.core.model.entity.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@Transactional
public class OrderService extends RepositoryService<Order, Long, OrderRepository> {

    private static final Sort ORDER_SORT = new Sort(DESC, "id");

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        super(orderRepository, Order.class);
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public void changeOrderState(Long orderId, OrderState state) {
        Order order = repository.getOne(orderId);
        order.setState(state);
    }

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return repository.findAll(ORDER_SORT);
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }
}
