package com.expertsoft.core.model;

import com.expertsoft.core.model.entity.MobilePhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<MobilePhone, Long> {}
