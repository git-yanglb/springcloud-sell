package com.sell.product.enums;

import lombok.Getter;

/**
 * 商品状态
 * 
 * @author Lenovo
 *
 */
@Getter
public enum ProductStatus {

	UP(0, "在架"), DOWN(1, "下架");

	private int code;

	private String desc;

	private ProductStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
