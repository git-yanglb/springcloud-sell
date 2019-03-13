package com.sell.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sell.product.dataobject.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

	/**
	 * 查询对应状态的商品列表
	 * 
	 * @param status
	 * @return
	 */
	List<ProductInfo> findByProductStatus(Integer status);

	List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
