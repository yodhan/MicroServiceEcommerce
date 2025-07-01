package com.ecommerce.microservice.inventory_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryRequestDTO {
    private String skuCode;
    private Integer quantity;
}
