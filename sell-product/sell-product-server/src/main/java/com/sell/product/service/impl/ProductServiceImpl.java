package com.sell.product.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sell.product.dataobject.ProductInfo;
import com.sell.product.dto.CartDTO;
import com.sell.product.enums.ProductStatus;
import com.sell.product.enums.ResultEnum;
import com.sell.product.exception.ProductException;
import com.sell.product.repository.ProductInfoRepository;
import com.sell.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductInfoRepository repository;

	@Override
	public List<ProductInfo> findUpAll() {
		return repository.findByProductStatus(ProductStatus.UP.getCode());
	}

	@Override
	public List<ProductInfo> findByProductIdIn(List<String> productIdList) {
		return repository.findByProductIdIn(productIdList);
	}

	@Override
	@Transactional
	public void decreasStock(List<CartDTO> cartDTOs) {
		for (CartDTO cartDTO : cartDTOs) {
			Optional<ProductInfo> productOptional = repository.findById(cartDTO.getProductId());
			if (!productOptional.isPresent()) {
				throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			ProductInfo productInfo = productOptional.get();
			int stock = productInfo.getProductStock() - cartDTO.getProductQuantity();
			if (stock < 0) {
				throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
			}

			productInfo.setProductStock(stock);
			repository.save(productInfo);
		}
	}

}
