package com.expertsoft.core.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expertsoft.core.model.entity.MobilePhone;

@Repository
public class JdbcProductDao implements ProductDao {

    private static final String FIND_ALL_MOBILE_PHONES = "SELECT id, model, display, length, width, color, price, camera FROM mobile_phone";
    private static final String FIND_MOBILE_PHONE_BY_ID = "SELECT id, model, display, length, width, color, price, camera FROM mobile_phone WHERE id = ?";
    private static final String FIND_MOBILE_PHONES_BY_IDS = "SELECT id, model, display, length, width, color, price, camera FROM mobile_phone WHERE id IN (:ids)";

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

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public JdbcProductDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MobilePhone> findAll() {
        return jdbcTemplate.query(FIND_ALL_MOBILE_PHONES, new MobilePhoneRowMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public MobilePhone findById(long id) {
        return jdbcTemplate.queryForObject(FIND_MOBILE_PHONE_BY_ID, new MobilePhoneRowMapper(), id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MobilePhone> findByIds(Set<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        return namedParameterJdbcTemplate.query(FIND_MOBILE_PHONES_BY_IDS, params, new MobilePhoneRowMapper());
    }

}