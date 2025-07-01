package com.ecommerce.microservice.product_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductRequest(
        String name,
        String description,
        BigDecimal price,
        String category,
        String brand,
        String imageUrl,
        Integer stockQuantity,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
