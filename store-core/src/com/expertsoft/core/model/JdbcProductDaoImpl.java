package com.expertsoft.core.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.expertsoft.core.model.entity.MobilePhone;

@Repository
public class JdbcProductDaoImpl implements JdbcProductDao {
	
	private static final String FIND_ALL_MOBILE_PHONES = "SELECT id, model, display, length, width, color, price, camera FROM mobile_phone";
	private static final String FIND_MOBILE_PHONE_BY_ID = "SELECT id, model, display, length, width, color, price, camera FROM mobile_phone WHERE id = ?";
	
	private static final class MobilePhoneRowMapper implements RowMapper<MobilePhone> {
		@Override
		public MobilePhone mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new MobilePhone(
				rs.getLong("id"),
				rs.getString("model"),
				rs.getString("display"),
				rs.getString("length"),
				rs.getString("width"),
				rs.getString("color"),
				rs.getDouble("price"),
				rs.getString("camera"));
		}
	}
	
	private JdbcOperations jdbcOperations;
	
	@Autowired
	public JdbcProductDaoImpl(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public List<MobilePhone> findAll() {
		return jdbcOperations.query(FIND_ALL_MOBILE_PHONES, new MobilePhoneRowMapper());
	}

	@Override
	public MobilePhone findById(long id) {
		return jdbcOperations.queryForObject(FIND_MOBILE_PHONE_BY_ID, new MobilePhoneRowMapper(), id);
	}

}
