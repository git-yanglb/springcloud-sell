package com.sell.order.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sell.common.util.KeyUtil;
import com.sell.order.dataobject.OrderDetail;
import com.sell.order.dataobject.OrderMaster;
import com.sell.order.dto.OrderDTO;
import com.sell.order.enums.OrderStatus;
import com.sell.order.enums.PayStatus;
import com.sell.order.enums.ResultEnum;
import com.sell.order.exception.OrderException;
import com.sell.order.repository.OrderDetailRepository;
import com.sell.order.repository.OrderMasterRepository;
import com.sell.order.service.OrderService;
import com.sell.product.client.ProductClient;
import com.sell.product.common.DecreaseStockInput;
import com.sell.product.common.ProductInfoOutput;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderMasterRepository orderMasterRepository;

	@Autowired
	private ProductClient productClient;

	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) {

		String orderId = KeyUtil.genUniqueKey();

		List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
		List<String> productIdList = orderDetailList.stream().map(OrderDetail::getProductId)
				.collect(Collectors.toList());

		List<ProductInfoOutput> productInfos = productClient.listForOrder(productIdList);
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
		// 计算总价
		for (OrderDetail orderDetail : orderDetailList) {
			for (ProductInfoOutput productInfo : productInfos) {
				if (orderDetail.getProductId().equals(productInfo.getProductId())) {
					orderAmount = orderAmount.add(
							productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
					BeanUtils.copyProperties(productInfo, orderDetail);
					orderDetail.setOrderId(orderId);
					orderDetail.setDetailId(KeyUtil.genUniqueKey());
					// 订单详情入库
					orderDetailRepository.save(orderDetail);
				}
			}
		}

		// 扣库存
		List<DecreaseStockInput> cartDTOs = orderDetailList.stream()
				.map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
				.collect(Collectors.toList());
		productClient.decreaseStock(cartDTOs);

		// 订单入库
		orderDTO.setOrderId(KeyUtil.genUniqueKey());
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
		orderMaster.setPayStatus(PayStatus.WAIT.getCode());
		orderMasterRepository.save(orderMaster);

		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO finish(String orderId) {
		Optional<OrderMaster> masterOptional = orderMasterRepository.findById(orderId);
		if (!masterOptional.isPresent()) {
			throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
		}

		OrderMaster orderMaster = masterOptional.get();
		if (OrderStatus.NEW.getCode() != orderMaster.getOrderStatus()) {
			throw new OrderException(ResultEnum.ORDER_STATUS_ERROR);
		}

		orderMaster.setOrderStatus(OrderStatus.FINISHED.getCode());
		orderMasterRepository.save(orderMaster);

		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
		if (CollectionUtils.isEmpty(orderDetailList)) {
			throw new OrderException(ResultEnum.ORDER_DETAILS_NOT_EXIST);
		}

		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);

		return orderDTO;
	}
}
