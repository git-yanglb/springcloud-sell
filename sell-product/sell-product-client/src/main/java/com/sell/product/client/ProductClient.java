package com.sell.product.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sell.product.common.DecreaseStockInput;
import com.sell.product.common.ProductInfoOutput;

@FeignClient(name = "SELL-PRODUCT", fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

	@PostMapping("/product/listForOrder")
	List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

	@PostMapping("/product/decreasStock")
	void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

	@Component
	static class ProductClientFallback implements ProductClient {

		@Override
		public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
			return null;
		}

		@Override
		public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

		}

	}
}
