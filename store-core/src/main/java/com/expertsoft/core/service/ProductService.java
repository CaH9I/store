package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.model.entity.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService extends RepositoryService<MobilePhone, Long, ProductRepository> {

    @Autowired
    public ProductService(ProductRepository productRepository) {
        super(productRepository, MobilePhone.class);
    }

    @Transactional(readOnly = true)
    public MobilePhone findById(Long id) {
        return repository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }
}
