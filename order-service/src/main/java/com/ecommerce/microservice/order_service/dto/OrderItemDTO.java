package com.ecommerce.microservice.order_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private String skuCode;
    private Integer quantity;
    private Double price;
}
