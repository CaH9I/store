package com.expertsoft.core.model;

import java.util.List;
import java.util.Set;

import com.expertsoft.core.model.entity.MobilePhone;

public interface ProductDao {

    List<MobilePhone> findAll();

    MobilePhone findById(long id);

    List<MobilePhone> findByIds(Set<Long> ids);
}
