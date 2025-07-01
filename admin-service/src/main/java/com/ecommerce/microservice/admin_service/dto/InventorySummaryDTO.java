package com.ecommerce.microservice.admin_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class InventorySummaryDTO {
    private long totalProducts;
    private long outOfStockItems;
    private long lowStockItems;

    public InventorySummaryDTO(int i, int i1) {
    }
}
