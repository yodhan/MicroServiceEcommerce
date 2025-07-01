package com.ecommerce.microservice.admin_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class OrderSummaryDTO {
    private long totalOrders;
    private double totalRevenue;
    private long activeUsers;

    public OrderSummaryDTO(int i, BigDecimal zero) {
    }
}
