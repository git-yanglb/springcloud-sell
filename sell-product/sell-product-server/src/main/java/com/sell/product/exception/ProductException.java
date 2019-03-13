package com.sell.product.exception;

import com.sell.product.enums.ResultEnum;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
