package com.sell.common.vo;

import lombok.Data;

@Data
public class ResultVO<T> {

	/**
	 * 错误码
	 */
	private Integer code;

	/**
	 * 消息
	 */
	private String msg;

	/**
	 * 数据
	 */
	private T data;

}
