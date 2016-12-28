package com.expertsoft.core.model;

import java.util.List;

import com.expertsoft.core.model.entity.MobilePhone;

public interface ProductDao {

    List<MobilePhone> findAll();

    MobilePhone findById(long id);
}
