package com.sell.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sell.order.dataobject.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

	List<OrderDetail> findByOrderId(String orderId);

}
