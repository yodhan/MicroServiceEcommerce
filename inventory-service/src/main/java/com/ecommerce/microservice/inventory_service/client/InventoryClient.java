package com.ecommerce.microservice.inventory_service.client;

import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    @GetExchange("/api/inventory")
    boolean isInStock();
}
