package com.ecommerce.microservice.inventory_service.controller;

import com.ecommerce.microservice.inventory_service.dto.InventoryRequestDTO;
import com.ecommerce.microservice.inventory_service.dto.InventoryResponseDTO;
import com.ecommerce.microservice.inventory_service.service.InventoryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }

    @PostMapping
    public ResponseEntity<InventoryResponseDTO> addOrUpdateStock(@RequestBody InventoryRequestDTO request) {
        return ResponseEntity.ok(inventoryService.addOrUpdateStock(request));
    }

    @PutMapping("/increase")
    public ResponseEntity<InventoryResponseDTO> increaseStock(@RequestBody InventoryRequestDTO request) {
        return ResponseEntity.ok(inventoryService.increaseStock(request));
    }

    @PostMapping("/check-and-reduce")
    public ResponseEntity<Boolean> checkAndReduceStock(@RequestBody InventoryRequestDTO request) {
        boolean result = inventoryService.checkAndReduceStock(request.getSkuCode(), request.getQuantity());
        return ResponseEntity.ok(result);
    }
}

