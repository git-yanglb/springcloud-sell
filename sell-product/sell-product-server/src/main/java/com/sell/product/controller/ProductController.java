package com.sell.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sell.common.util.ResultVOUtil;
import com.sell.common.vo.ResultVO;
import com.sell.product.dataobject.ProductCategory;
import com.sell.product.dataobject.ProductInfo;
import com.sell.product.dto.CartDTO;
import com.sell.product.service.CategoryService;
import com.sell.product.service.ProductService;
import com.sell.product.vo.ProductInfoVO;
import com.sell.product.vo.ProductVO;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public ResultVO<List<ProductVO>> list() {
		// 1. 查询所有在架商品
		List<ProductInfo> products = productService.findUpAll();
		List<Integer> categoryTypeList = products.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
		// 2. 查询商品类目
		List<ProductCategory> categoryTypes = categoryService.findByCategoryTypeIn(categoryTypeList);
		
		List<ProductVO> productVOs = new ArrayList<ProductVO>();
		for (ProductCategory category : categoryTypes) {
			ProductVO vo = new ProductVO();
			vo.setCategoryName(category.getCategoryName());
			vo.setCategoryType(category.getCategoryType());
			List<ProductInfoVO> infoVOs = new ArrayList<>();
			for (ProductInfo product : products) {
				if (category.getCategoryType() == product.getCategoryType()) {
					ProductInfoVO infoVO = new ProductInfoVO();
					BeanUtils.copyProperties(product, infoVO);
					infoVOs.add(infoVO);
				}
			}
			vo.setProductInfoVOList(infoVOs);
			productVOs.add(vo);
		}
		return ResultVOUtil.success(productVOs);
	}

	@PostMapping("/listForOrder")
	public List<ProductInfo> listForOrder(@RequestBody List<String> productIdList) {
		return productService.findByProductIdIn(productIdList);
	}

	@PostMapping("/decreasStock")
	public void decreasStock(@RequestBody List<CartDTO> cartDTOs) {
		productService.decreasStock(cartDTOs);
	}

}
