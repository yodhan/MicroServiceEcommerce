package com.ecommerce.microservice.product_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        String category,
        String brand,
        String imageUrl,
        Integer stockQuantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean isActive
) {}
