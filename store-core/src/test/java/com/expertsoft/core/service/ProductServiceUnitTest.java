package com.expertsoft.core.service;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.model.entity.MobilePhone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceUnitTest {

    private static Long phoneId = 1L;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private MobilePhone phone = new MobilePhone();

    @Test
    public void getAll() {
        List<MobilePhone> phones = singletonList(phone);
        when(productRepository.findAll()).thenReturn(phones);

        List<MobilePhone> result = productService.getAll();

        assertEquals(phones, result);
    }

    @Test
    public void getById() {
        when(productRepository.findOne(phoneId)).thenReturn(phone);

        MobilePhone result = productService.getById(phoneId);

        assertEquals(phone, result);
    }
}
