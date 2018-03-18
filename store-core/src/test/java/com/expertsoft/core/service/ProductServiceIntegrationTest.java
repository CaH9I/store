package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.entity.MobilePhone;
import com.expertsoft.core.test.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhones;
import static org.junit.Assert.assertEquals;

public class ProductServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findById() {
        MobilePhone testPhone = getTestMobilePhone();

        MobilePhone phone = productService.findById(testPhone.getId());

        assertEquals(testPhone, phone);
    }

    @Test(expected = RecordNotFoundException.class)
    public void findByIdNotExists() {
        productService.findById(0L);
    }

    @Test
    public void findAll() {
        List<MobilePhone> phones = productService.findAll();

        assertEquals(getTestMobilePhones(), phones);
    }
}
