package com.service;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.TenantContext;
import com.domain.Order;
import com.domain.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Transactional
	public Order addOrder(String tenantName){
		TenantContext.setCurrentTenant(tenantName);
		System.out.println("Tanent name ="+tenantName);
        Order newOrder = new Order(new Date(System.currentTimeMillis()));
        orderRepository.save(newOrder);
        return newOrder;
	}
}
