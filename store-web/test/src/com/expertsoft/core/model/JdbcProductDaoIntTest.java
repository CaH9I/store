package com.expertsoft.core.model;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/context.xml")
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

    @Test
    public void findMobilePhonesByIds() {
        productDao.findByIds(Collections.singleton(1L));
    }

}
