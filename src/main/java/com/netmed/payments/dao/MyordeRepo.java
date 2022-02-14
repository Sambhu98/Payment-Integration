package com.netmed.payments.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netmed.payments.entities.MyOrder;

public interface MyordeRepo extends JpaRepository<MyOrder, Long> {
	public MyOrder findByOrderId(String orderid);
}
