package com.expertsoft.core.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcDeliveryDao implements DeliveryDao {

    private static final String GET_DEFAULT_DELIVERY_AMOUNT = "SELECT amount FROM fixed_delivery_price";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcDeliveryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public double findFixedDeliveryAmount() {
        return jdbcTemplate.queryForObject(GET_DEFAULT_DELIVERY_AMOUNT, Double.class);
    }

}
