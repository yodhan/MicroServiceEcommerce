package com.ecommerce.microservice.inventory_service.service;

import com.ecommerce.microservice.inventory_service.dto.InventoryRequestDTO;
import com.ecommerce.microservice.inventory_service.dto.InventoryResponseDTO;
import com.ecommerce.microservice.inventory_service.model.Inventory;
import com.ecommerce.microservice.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

    @Transactional
    public boolean checkAndReduceStock(String skuCode, Integer quantity) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElse(null);

        if (inventory != null && inventory.getQuantity() >= quantity) {
            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }

    @Transactional
    public InventoryResponseDTO addOrUpdateStock(InventoryRequestDTO request) {
        Inventory inventory = inventoryRepository.findBySkuCode(request.getSkuCode())
                .orElse(new Inventory());

        inventory.setSkuCode(request.getSkuCode());
        inventory.setQuantity(request.getQuantity());
        inventoryRepository.save(inventory);

        return InventoryResponseDTO.builder()
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .isAvailable(inventory.getQuantity() > 0)
                .build();
    }

    @Transactional
    public InventoryResponseDTO increaseStock(InventoryRequestDTO request) {
        Inventory inventory = inventoryRepository.findBySkuCode(request.getSkuCode())
                .orElseThrow(() -> new RuntimeException("SKU not found: " + request.getSkuCode()));

        inventory.setQuantity(inventory.getQuantity() + request.getQuantity());
        inventoryRepository.save(inventory);

        return InventoryResponseDTO.builder()
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .isAvailable(true)
                .build();
    }
}
