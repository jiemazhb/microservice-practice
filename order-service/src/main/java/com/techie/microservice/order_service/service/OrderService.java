package com.techie.microservice.order_service.service;

import com.techie.microservice.order_service.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
