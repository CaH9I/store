package com.expertsoft.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsoft.core.model.JdbcProductDao;
import com.expertsoft.core.model.entity.MobilePhone;

@Service
public class ProductService {
	
	private JdbcProductDao productDao;
	
	@Autowired
	public ProductService(JdbcProductDao productDao) {
		this.productDao = productDao;
	}
	
	public List<MobilePhone> getAll() {
		return productDao.findAll();
	}
	
	public MobilePhone getById(long id) {
		return productDao.findById(id);
	}
}
