package com.expertsoft.core.service;

import com.expertsoft.core.CoreApplication;
import com.expertsoft.core.model.entity.MobilePhone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.expertsoft.core.TestObjectFactory.getTestMobilePhone;
import static com.expertsoft.core.TestObjectFactory.getTestMobilePhones;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CoreApplication.class)
@Transactional
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    public void getById() {
        MobilePhone testPhone = getTestMobilePhone();

        MobilePhone phone = productService.getById(testPhone.getId());

        assertEquals(testPhone, phone);
    }

    @Test
    public void getAll() {
        List<MobilePhone> phones = productService.getAll();

        assertEquals(getTestMobilePhones(), phones);
    }
}
