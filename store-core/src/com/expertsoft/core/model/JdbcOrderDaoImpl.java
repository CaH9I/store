package com.expertsoft.core.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.Order;

@Repository
public class JdbcOrderDaoImpl implements JdbcOrderDao {
	
	private static final String INSERT_ORDER = "INSERT INTO store_order(first_name,last_name,address,phone,additional_info) VALUES(?,?,?,?,?)";
	private static final String INSERT_COMMERCE_ITEM = "INSERT INTO commerce_item(product_id,price,quantity) VALUES(?,?,?)";
	private static final String INSERT_ORDER_COMMERCE_ITEM_REF = "INSERT INTO order_commerce_item(order_id,commerce_item_id) VALUES(?,?)";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcOrderDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void save(Order order) {
		try {
			long orderId = insertOrder(order);
			List<Long> ciIds = insertCommerceItems(order.getCommerceItems());
			insertOrderCommerceItemRefs(orderId, ciIds);
		} catch (SQLException e) {
		}
	}
	
	private long insertOrder(Order order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, order.getFirstName());
			statement.setString(2, order.getLastname());
			statement.setString(3, order.getAddress());
			statement.setString(4, order.getPhoneNumber());
			statement.setString(5, order.getAdditionalInfo());
			return statement;
		}, keyHolder);
		
		return Long.parseLong(keyHolder.getKeys().get("id").toString());
	}
	
	private List<Long> insertCommerceItems(List<CommerceItem> items) throws SQLException {
		List<Long> keys = new ArrayList<>();
		
		try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(INSERT_COMMERCE_ITEM, Statement.RETURN_GENERATED_KEYS)) {
				
				for (CommerceItem ci : items) {
					statement.setLong(1, ci.getPhone().getId());
					statement.setDouble(2, ci.getPrice());
					statement.setInt(3, ci.getQuantity());
					
					statement.executeUpdate();
					
					try (ResultSet generatedkeys = statement.getGeneratedKeys()) {
						generatedkeys.next();
						keys.add(generatedkeys.getLong("id"));
					}
				}
				
			}
		}
		
		return keys;
	}
	
	private void insertOrderCommerceItemRefs(long orderId, List<Long> ciIds) {
		jdbcTemplate.batchUpdate(INSERT_ORDER_COMMERCE_ITEM_REF, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement statement, int index) throws SQLException {
				statement.setLong(1, orderId);
				statement.setLong(2, ciIds.get(index));
			}
			
			@Override
			public int getBatchSize() {
				return ciIds.size();
			}
		});
	}

}
