package com.expertsoft.core.service;

import java.util.List;

import com.expertsoft.core.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.entity.MobilePhone;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<MobilePhone> getAll() {
        return productRepository.findAll();
    }

    public MobilePhone getById(Long id) {
        return productRepository.findOne(id);
    }
}
