package com.expertsoft.core.service;

import com.expertsoft.core.exception.EntityNotFoundException;
import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.model.entity.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        MobilePhone result = productRepository.findOne(id);

        if (result != null) {
            return result;
        }

        throw new EntityNotFoundException();
    }
}
