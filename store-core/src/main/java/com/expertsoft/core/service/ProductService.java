package com.expertsoft.core.service;

import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.model.entity.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends RepositoryService<MobilePhone, Long, ProductRepository> {

    @Autowired
    public ProductService(ProductRepository productRepository) {
        super(productRepository, MobilePhone.class);
    }
}
