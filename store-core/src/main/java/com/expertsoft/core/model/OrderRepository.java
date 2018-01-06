package com.expertsoft.core.model;

import com.expertsoft.core.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o " +
            "join fetch o.commerceItems ci " +
            "join fetch ci.phone " +
            "where o.id = :id")
    Order findOneWithItemsAndProducts(@Param("id") Long id);
}
