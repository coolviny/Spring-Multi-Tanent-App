package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Order;
import com.service.OrderService;

@RestController
public class OrderController {
	
    @Autowired
    private OrderService orderService;

    @RequestMapping(path = "/orders", method= RequestMethod.POST)
    public ResponseEntity<?> createSampleOrder(@RequestHeader("X-TenantID") String tenantName) {
       System.out.println("Request came to controller");
       Order order = orderService.addOrder(tenantName);
       return ResponseEntity.ok(order);
    }
    
}
