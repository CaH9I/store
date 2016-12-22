package com.expertsoft.core.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expertsoft.core.model.entity.CommerceItem;
import com.expertsoft.core.model.entity.Order;

@Repository
public class JdbcOrderDaoImpl implements JdbcOrderDao {
	
	private static final String INSERT_ORDER = "INSERT INTO store_order(first_name,last_name,address,phone,additional_info) VALUES(?,?,?,?,?)";
	private static final String INSERT_COMMERCE_ITEM = "INSERT INTO commerce_item(order_id,product_id,price,quantity) VALUES(?,?,?,?)";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcOrderDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public void save(Order order) {
		long orderId = insertOrder(order);
		insertCommerceItems(orderId, order.getCommerceItems());
	}
	
	private long insertOrder(Order order) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection -> {
			PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, order.getFirstName());
			statement.setString(2, order.getLastName());
			statement.setString(3, order.getAddress());
			statement.setString(4, order.getPhoneNumber());
			statement.setString(5, order.getAdditionalInfo());
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

}
