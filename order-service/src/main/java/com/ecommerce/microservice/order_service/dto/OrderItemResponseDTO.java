package com.ecommerce.microservice.order_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {
    private String productId;
    private Integer quantity;
    private Double price;
}
