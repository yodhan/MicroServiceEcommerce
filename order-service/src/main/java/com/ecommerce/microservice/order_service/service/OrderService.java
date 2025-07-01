package com.ecommerce.microservice.order_service.service;

import com.ecommerce.microservice.order_service.dto.OrderRequest;
import com.ecommerce.microservice.order_service.dto.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest) throws JsonProcessingException;
    OrderResponse getOrderByOrderNumber(String orderNumber);
    List<OrderResponse> getAllOrdersByUserId(String userId);
    List<OrderResponse> getAllOrders();
    void cancelOrder(Long orderId);

}
