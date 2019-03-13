package com.sell.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sell.product.dataobject.ProductCategory;
import com.sell.product.repository.ProductCategoryRepository;
import com.sell.product.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private ProductCategoryRepository repository;
	
	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

}
