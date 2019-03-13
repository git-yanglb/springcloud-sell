package com.sell.product.service;

import java.util.List;

import com.sell.product.dataobject.ProductInfo;
import com.sell.product.dto.CartDTO;

public interface ProductService {

	List<ProductInfo> findUpAll();

	List<ProductInfo> findByProductIdIn(List<String> productIdList);

	void decreasStock(List<CartDTO> cartDTOs);
}
