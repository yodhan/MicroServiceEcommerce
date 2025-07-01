package com.ecommerce.microservice.cart_service.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private String productId;
    private Integer quantity;
}
