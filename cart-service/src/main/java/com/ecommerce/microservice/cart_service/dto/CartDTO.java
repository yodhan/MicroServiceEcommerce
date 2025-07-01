package com.ecommerce.microservice.cart_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private String userId;
    private List<CartItemDTO> items;
}
