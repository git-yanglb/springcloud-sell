package com.sell.product.service;

import java.util.List;

import com.sell.product.dataobject.ProductCategory;

public interface CategoryService {

	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
