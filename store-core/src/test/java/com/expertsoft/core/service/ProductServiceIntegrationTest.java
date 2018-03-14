package com.expertsoft.core.service;

import com.expertsoft.core.exception.EntityNotFoundException;
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
    public void getById() {
        MobilePhone testPhone = getTestMobilePhone();

        MobilePhone phone = productService.getById(testPhone.getId());

        assertEquals(testPhone, phone);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getByIdNotExists() {
        productService.getById(0L);
    }

    @Test
    public void getAll() {
        List<MobilePhone> phones = productService.getAll();

        assertEquals(getTestMobilePhones(), phones);
    }
}
