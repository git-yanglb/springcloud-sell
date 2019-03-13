package com.sell.order.exception;

import com.sell.order.enums.ResultEnum;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer code;

	public OrderException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public OrderException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}
}
