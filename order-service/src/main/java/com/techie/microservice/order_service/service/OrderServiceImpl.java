package com.techie.microservice.order_service.service;

import com.techie.microservice.order_service.client.InventoryClient;
import com.techie.microservice.order_service.dto.OrderRequest;
import com.techie.microservice.order_service.model.Order;
import com.techie.microservice.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        log.info("testing..... by Danny");
        var isProductInStock = this.inventoryClient.isInStock(orderRequest.getSkuCode(), orderRequest.getQuantity());

        if(isProductInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.getPrice());
            order.setQuantity(orderRequest.getQuantity());
            order.setSkuCode(orderRequest.getSkuCode());

            this.orderRepository.save(order);
        }else{
            throw new RuntimeException("Producdt with SkuCode " + orderRequest.getSkuCode() + " is not in the stock");
        }


    }
}
