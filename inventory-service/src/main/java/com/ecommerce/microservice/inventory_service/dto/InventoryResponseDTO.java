package com.ecommerce.microservice.inventory_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponseDTO {
    private String skuCode;
    private Integer quantity;
    private boolean isAvailable;
}
