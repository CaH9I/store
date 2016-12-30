package com.expertsoft.core.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.model.entity.Order;

@Repository
public class JdbcOrderDao implements OrderDao {

    private static final String INSERT_ORDER = "INSERT INTO store_order(first_name,last_name,address,phone,subtotal,delivery_amount,total,additional_info) VALUES(?,?,?,?,?,?,?,?)";
    private static final String INSERT_COMMERCE_ITEM = "INSERT INTO commerce_item(order_id,product_id,price,quantity) VALUES(?,?,?,?)";
    private static final String FIND_ALL_ORDERS = "SELECT o.id,o.first_name,o.last_name,o.address,o.phone,o.subtotal,o.delivery_amount,o.total,o.additional_info,c.product_id,c.price,c.quantity,m.model,m.display,m.length,m.width,m.color,m.camera FROM store_order o, commerce_item c, mobile_phone m WHERE c.order_id=o.id AND c.product_id=m.id";
    private static final String FIND_ORDER_BY_ID = "SELECT o.id,o.first_name,o.last_name,o.address,o.phone,o.subtotal,o.delivery_amount,o.total,o.additional_info,c.product_id,c.price,c.quantity,m.model,m.display,m.length,m.width,m.color,m.camera FROM store_order o, commerce_item c, mobile_phone m WHERE c.order_id=o.id AND c.product_id=m.id AND o.id=?";
    private static final String DELETE_COMMERCE_ITEMS_BY_ORDER_ID = "DELETE FROM commerce_item WHERE order_id = ?";
    private static final String DELETE_ORDER_BY_ORDER_ID = "DELETE FROM store_order WHERE id = ?";

    private static class OrderRowCallbackHandler implements RowCallbackHandler {

        private List<Order> orders = new ArrayList<>();

        @Override
        public void processRow(ResultSet rs) throws SQLException {
            MobilePhone mPhone = new MobilePhone(
                rs.getLong("product_id"),
                rs.getString("model"),
                rs.getString("display"),
                rs.getString("length"),
                rs.getString("width"),
                rs.getString("color"),
                rs.getDouble("price"),
                rs.getString("camera"));

            CommerceItem ci = new CommerceItem(
                mPhone,
                rs.getInt("quantity"),
                rs.getDouble("price"));

            long orderId = rs.getLong("id");
            Order order = orders.stream().filter(o -> o.getId() == orderId).findFirst().orElse(null);

            if (order == null) {
                order = new Order(
                    orderId,
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("additional_info"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("delivery_amount"),
                    rs.getDouble("total"));
                order.getCommerceItems().add(ci);
                orders.add(order);
            } else {
                order.getCommerceItems().add(ci);
            }
        }

        public List<Order> getOrders() {
            return orders;
        }

    }

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public long save(Order order) {
        long orderId = insertOrder(order);
        insertCommerceItems(orderId, order.getCommerceItems());
        return orderId;
    }

    private long insertOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getFirstName());
            statement.setString(2, order.getLastName());
            statement.setString(3, order.getAddress());
            statement.setString(4, order.getPhoneNumber());
            statement.setDouble(5, order.getSubtotal());
            statement.setDouble(6, order.getDelivery());
            statement.setDouble(7, order.getTotal());
            statement.setString(8, order.getAdditionalInfo());
            return statement;
        }, keyHolder);

        return Long.parseLong(keyHolder.getKeys().get("id").toString());
    }

    private void insertCommerceItems(long orderId, List<CommerceItem> items) {
        jdbcTemplate.batchUpdate(INSERT_COMMERCE_ITEM, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement statement, int index) throws SQLException {
                statement.setLong(1, orderId);
                statement.setLong(2, items.get(index).getPhone().getId());
                statement.setDouble(3, items.get(index).getPrice());
                statement.setInt(4, items.get(index).getQuantity());
            }

            @Override
            public int getBatchSize() {
                return items.size();
            }
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        OrderRowCallbackHandler rowHandler = new OrderRowCallbackHandler();
        jdbcTemplate.query(FIND_ALL_ORDERS, rowHandler);
        return rowHandler.getOrders();
    }

    @Override
    @Transactional
    public void deleteById(long orderId) {
        jdbcTemplate.update(DELETE_COMMERCE_ITEMS_BY_ORDER_ID, orderId);
        jdbcTemplate.update(DELETE_ORDER_BY_ORDER_ID, orderId);
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(long orderId) {
        OrderRowCallbackHandler rowHandler = new OrderRowCallbackHandler();
        jdbcTemplate.query(FIND_ORDER_BY_ID, rowHandler, orderId);
        List<Order> orders = rowHandler.getOrders();

        if (orders.isEmpty()) {
            throw new RecordNotFoundException();
        }

        return orders.get(0);
    }

}
