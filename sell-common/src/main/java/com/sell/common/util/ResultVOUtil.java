package com.sell.common.util;

import com.sell.common.vo.ResultVO;

public class ResultVOUtil {

	public static <T> ResultVO<T> success(T object) {
		ResultVO<T> resultVO = new ResultVO<T>();
		resultVO.setData(object);
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		return resultVO;
	}
}
