package com.sell.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sell.order.dataobject.OrderDetail;
import com.sell.order.dto.OrderDTO;
import com.sell.order.enums.ResultEnum;
import com.sell.order.exception.OrderException;
import com.sell.order.form.OrderForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderForm2OrderDTOConverter {

	public static OrderDTO convert(OrderForm orderForm) {
		Gson gson = new Gson();
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		orderDTO.setBuyerAddress(orderForm.getAddress());
		orderDTO.setBuyerOpenid(orderForm.getOpenid());

		List<OrderDetail> orderDetailList = new ArrayList<>();
		try {
			orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
			}.getType());
		} catch (Exception e) {
			log.error("【json转换】错误, string={}", orderForm.getItems());
			throw new OrderException(ResultEnum.PARAM_ERROR);
		}
		orderDTO.setOrderDetailList(orderDetailList);

		return orderDTO;
	}
	
}
