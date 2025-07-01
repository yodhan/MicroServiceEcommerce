package com.ecommerce.microservice.order_service.mapper;

import com.ecommerce.microservice.order_service.dto.OrderItemResponseDTO;
import com.ecommerce.microservice.order_service.dto.OrderRequest;
import com.ecommerce.microservice.order_service.dto.OrderResponse;
import com.ecommerce.microservice.order_service.model.Order;
import com.ecommerce.microservice.order_service.model.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toOrderEntity(OrderRequest request) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setUserId(request.getUserId());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());
        List<OrderItem> items = request.getItems().stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setSkuCode(item.getSkuCode());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getPrice());
                    orderItem.setOrder(order);
                    return orderItem;
                }).toList();
        double total = items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        order.setItems(items);
        order.setTotalAmount(total);
        return order;
    }

    public static OrderResponse toOrderResponse(Order order) {
        List<OrderItemResponseDTO> itemDtos = order.getItems().stream()
                .map(item -> new OrderItemResponseDTO(
                        item.getSkuCode(),
                        item.getQuantity(),
                        item.getPrice()
                )).toList();

        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getOrderDate(),
                itemDtos
        );
    }
    public static List<OrderResponse> toDTOList(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::toOrderResponse)
                .collect(Collectors.toList());
    }
}