package com.ecommerce.microservice.order_service.service.impl;

import com.ecommerce.microservice.order_service.client.InventoryClient;
import com.ecommerce.microservice.order_service.client.OrderEventPublisher;
import com.ecommerce.microservice.order_service.dto.OrderItemDTO;
import com.ecommerce.microservice.order_service.dto.OrderRequest;
import com.ecommerce.microservice.order_service.dto.OrderResponse;
import com.ecommerce.microservice.order_service.mapper.OrderMapper;
import com.ecommerce.microservice.order_service.model.Order;
import com.ecommerce.microservice.order_service.repository.OrderRepository;
import com.ecommerce.microservice.order_service.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final OrderEventPublisher orderEventPublisher;
    private final ObjectMapper objectMapper; // Jackson


    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) throws JsonProcessingException {
        // Validate inventory for each item
        for (OrderItemDTO item : orderRequest.getItems()) {
            boolean inStock = inventoryClient.isInStock(item.getSkuCode(), item.getQuantity());
            if (!inStock) {
                throw new RuntimeException("Product " + item.getSkuCode() + " is not in stock");
            }
        }

       
        Order order = OrderMapper.toOrderEntity(orderRequest);
        Order savedOrder = orderRepository.save(order);
        // Serialize and publish to Kafka
        String orderEvent = objectMapper.writeValueAsString(savedOrder);
        orderEventPublisher.publishOrderPlaceEvent(orderEvent);

        return OrderMapper.toOrderResponse(savedOrder);
    }


    @Override
    public OrderResponse getOrderByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(()-> new RuntimeException("Order not found with ID: "+ orderNumber));
        return OrderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return OrderMapper.toDTOList(orders);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new RuntimeException("Order not found with Id:"+orderId));
        order.setStatus("CANCELLED");
        orderRepository.save(order);

    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper::toOrderResponse)
                .toList();
    }
}
