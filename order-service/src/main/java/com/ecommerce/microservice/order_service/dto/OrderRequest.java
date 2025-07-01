package com.ecommerce.microservice.order_service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    private String userId;
    private List<OrderItemDTO> items;
}
