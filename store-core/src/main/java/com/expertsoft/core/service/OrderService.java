package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.OrderRepository;
import com.expertsoft.core.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@Transactional
public class OrderService extends RepositoryService<Order, Long, OrderRepository> {

    private static final Sort ORDER_SORT = Sort.by(DESC, "id");

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        super(orderRepository, Order.class);
    }

    public Order save(Order order) {
        return repository.save(order);
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
