//package com.expertsoft.core.model;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:config/core-context.xml")
//public class JdbcDeliveryDaoIntTest {
//
//    private DeliveryDao deliveryDao;
//
//    @Autowired
//    public void setDeliveryDao(DeliveryDao deliveryDao) {
//        this.deliveryDao = deliveryDao;
//    }
//
//    @Test
//    public void findFixedDeliveryAmount() {
//        Assert.assertEquals(5, deliveryDao.findFixedDeliveryAmount(), 0);
//    }
//}
