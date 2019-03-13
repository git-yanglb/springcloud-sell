package org.sell.product.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.sell.product.AppTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sell.product.dataobject.ProductInfo;
import com.sell.product.repository.ProductInfoRepository;

@Component
public class ProductInfoRepositoryTest extends AppTest {

	@Autowired
	private ProductInfoRepository repository;
	
	@Test
	public void test() {
		List<ProductInfo> productInfos = repository.findByProductStatus(0);
		Assert.assertTrue(productInfos.size() == 2);
	}

}
