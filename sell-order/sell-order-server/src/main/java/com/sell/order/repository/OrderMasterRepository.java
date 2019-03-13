package com.sell.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sell.order.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
