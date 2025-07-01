package com.ecommerce.microservice.order_service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private String status;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private List<OrderItemResponseDTO> items;


    public OrderResponse(String id, String orderNumber, String status, Double totalAmount, LocalDateTime orderDate) {
    }
}
