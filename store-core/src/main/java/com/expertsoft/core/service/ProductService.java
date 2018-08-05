package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import com.expertsoft.core.model.ProductRepository;
import com.expertsoft.core.model.entity.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProductService extends RepositoryService<MobilePhone, Long, ProductRepository> {

    @Value("${productList.pageSize:8}")
    private int pageSize;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        super(productRepository, MobilePhone.class);
    }

    public MobilePhone findById(Long id) {
        return repository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }

    public Page<MobilePhone> findAll(int page) {
        if (page < 0 || pageOffsetOverflow(page)) {
            throw new RecordNotFoundException();
        }

        var result = findAll(page, pageSize);

        if ((page > 0) && (!result.hasContent())) {
            throw new RecordNotFoundException();
        }
        return result;
    }

    private boolean pageOffsetOverflow(long page) {
        return page * pageSize > Integer.MAX_VALUE;
    }
}
