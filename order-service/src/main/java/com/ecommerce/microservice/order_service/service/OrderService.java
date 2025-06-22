package com.ecommerce.microservice.order_service.service;

import com.ecommerce.microservice.order_service.client.InventoryClient;
import com.ecommerce.microservice.order_service.dto.OrderRequest;
import com.ecommerce.microservice.order_service.dto.OrderResponse;
import com.ecommerce.microservice.order_service.model.Order;
import com.ecommerce.microservice.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private  final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
    }

    public void placeOrder(OrderRequest orderRequest){
        var isProdutInStock=inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());
        if (isProdutInStock){
            Order order= new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
        }
        else{
            throw new RuntimeException("Out of stock" + orderRequest.skuCode());
        }

    }
}
