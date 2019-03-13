package org.sell.product.repository;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.sell.product.AppTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sell.product.dataobject.ProductCategory;
import com.sell.product.repository.ProductCategoryRepository;

@Component
public class ProductCategoryRepositoryTest extends AppTest {

	@Autowired
	private ProductCategoryRepository repository;

	@Test
	public void test() {
		List<ProductCategory> categoryTypeIn = repository.findByCategoryTypeIn(Arrays.asList(11, 12));
		assertTrue(categoryTypeIn.size() == 1);
	}

}
