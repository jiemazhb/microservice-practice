package com.techie.microservice.order_service.service;

import com.techie.microservice.order_service.dto.OrderRequest;
import com.techie.microservice.order_service.model.Order;
import com.techie.microservice.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.getPrice());
        order.setQuantity(orderRequest.getQuantity());
        order.setSkuCode(orderRequest.getSkuCode());

        this.orderRepository.save(order);
    }
}
