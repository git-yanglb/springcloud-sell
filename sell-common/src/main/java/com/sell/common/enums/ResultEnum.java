package com.sell.common.enums;

import com.sell.common.vo.ResultVO;

public enum ResultEnum {

	SUCCESS(0, "操作成功"), FAILURE(-99, "操作失败");

	private Integer code;

	private String msg;

	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public <T> ResultVO<T> getResult(T data) {
		return getResult(this.code, this.msg, data);
	}

	public <T> ResultVO<T> getResult(Integer code, T data) {
		return getResult(code, this.msg, data);
	}

	public <T> ResultVO<T> getResult(String msg, T data) {
		return getResult(this.code, msg, data);
	}

	public <T> ResultVO<T> getResult(String msg, Class<T> clzz) {
		return getResult(this.code, msg, null);
	}

	public <T> ResultVO<T> getResult(Integer code, String msg, T data) {
		ResultVO<T> resultVO = new ResultVO<T>();
		resultVO.setCode(code);
		resultVO.setMsg(msg);
		resultVO.setData(data);
		return resultVO;
	}

}
