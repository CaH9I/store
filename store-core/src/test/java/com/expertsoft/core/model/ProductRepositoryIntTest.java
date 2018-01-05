//package com.expertsoft.core.model;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.expertsoft.core.model.entity.MobilePhone;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:config/core-context.xml")
//public class ProductRepositoryIntTest {
//
//    private ProductRepository productRepository;
//
//    @Autowired
//    public void setProductDao(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @Test
//    public void findAllMobilePhones() {
//        List<MobilePhone> phones = productRepository.findAll();
//        assertFalse(phones.isEmpty());
//    }
//
//    @Test
//    public void findMobilePhoneById() {
//        MobilePhone phone = productRepository.findOne(1L);
//        assertEquals(1L, phone.getId().longValue());
//    }
//
//    @Test
//    public void findMobilePhonesByIds() {
//        Set<Long> ids = new HashSet<>();
//        ids.add(1L);
//        ids.add(3L);
//        List<MobilePhone> phones = productRepository.findByIdIn(ids);
//        assertEquals(2, phones.size());
//    }
//
//}
