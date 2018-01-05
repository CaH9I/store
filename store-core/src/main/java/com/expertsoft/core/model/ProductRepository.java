package com.expertsoft.core.model;

import java.util.List;
import java.util.Set;

import com.expertsoft.core.model.entity.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<MobilePhone, Long> {

    List<MobilePhone> findByIdIn(Set<Long> ids);
}
