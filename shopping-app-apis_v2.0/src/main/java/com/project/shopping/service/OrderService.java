package com.project.shopping.service;

import com.project.shopping.entity.Orders;
import com.project.shopping.payloads.OrderRequest;
import com.project.shopping.payloads.orderResponse;

public interface OrderService {
	
	Orders orderCreate(OrderRequest request);
	
}
