package com.expertsoft.core.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.expertsoft.core.configuration.TestDataSourceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JdbcProductDao.class, TestDataSourceConfig.class})
public class JdbcProductDaoIntTest {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Test
    public void findAllMobilePhones() {
        productDao.findAll();
    }

    @Test
    public void findMobilePhoneById() {
        productDao.findById(1);
    }

}
