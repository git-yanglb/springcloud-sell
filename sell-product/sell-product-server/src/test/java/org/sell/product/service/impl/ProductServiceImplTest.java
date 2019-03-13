package org.sell.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.sell.product.AppTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sell.product.dto.CartDTO;
import com.sell.product.service.ProductService;

@Component
public class ProductServiceImplTest extends AppTest {

	@Autowired
	private ProductService productService;

	@Test
	public void test() {
		List<CartDTO> cartDTOs = new ArrayList<CartDTO>();
		cartDTOs.add(new CartDTO("157875196366160022", 2));
		productService.decreasStock(cartDTOs);
	}

}
