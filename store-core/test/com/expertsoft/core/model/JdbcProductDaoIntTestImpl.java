package com.expertsoft.core.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.expertsoft.core.configuration.TestDataSourceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JdbcProductDaoImpl.class, TestDataSourceConfig.class})
public class JdbcProductDaoIntTestImpl implements JdbcProductDaoIntTest {
	
	private JdbcProductDao productDao;
	
	@Autowired
	public void setProductDao(JdbcProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Test
	@Override
	public void findAllMobilePhones() {
		assertNotNull(productDao.findAll());
	}
	
	@Test
	@Override
	public void findMobilePhoneById() {
		assertNotNull(productDao.findById(1));
	}

}
