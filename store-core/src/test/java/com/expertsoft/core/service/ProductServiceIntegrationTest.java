package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.test.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;

import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhone;
import static com.expertsoft.core.test.TestObjectFactory.getTestMobilePhones;
import static org.junit.Assert.assertEquals;

public class ProductServiceIntegrationTest extends IntegrationTest {

    private static final int PAGE_NUMBER = 0;

    @Value("${productList.pageSize}")
    private int pageSize;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findById() {
        var testPhone = getTestMobilePhone();

        var phone = productService.findById(testPhone.getId());

        assertEquals(testPhone, phone);
    }

    @Test(expected = RecordNotFoundException.class)
    public void findByIdNotExists() {
        productService.findById(0L);
    }

    @Test
    public void findAll() {
        var phones = productService.findAll();

        assertEquals(getTestMobilePhones(), phones);
    }

    @Test
    public void findAllForPage() {
        var phones = productService.findAll(PAGE_NUMBER);

        assertEquals(productRepository.findAll(PageRequest.of(PAGE_NUMBER, pageSize)), phones);
    }

    @Test(expected = RecordNotFoundException.class)
    public void findAllForPageUnbound() {
        productService.findAll(1000);
    }
}
