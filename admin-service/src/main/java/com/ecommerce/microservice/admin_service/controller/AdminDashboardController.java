package com.ecommerce.microservice.admin_service.controller;

import com.ecommerce.microservice.admin_service.dto.InventorySummaryDTO;
import com.ecommerce.microservice.admin_service.dto.OrderSummaryDTO;
import com.ecommerce.microservice.admin_service.dto.UserResponseDTO;
import com.ecommerce.microservice.admin_service.service.AdminDashboardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(adminDashboardService.getAllUsers());
    }

    @PutMapping("/users/{userId}/status")
    public ResponseEntity<Void> updateUserStatus(
            @PathVariable String userId,
            @RequestParam boolean enabled
    ) {
        adminDashboardService.updateUserStatus(userId, enabled);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        adminDashboardService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/summary")
    public ResponseEntity<OrderSummaryDTO> getOrderSummary() {
        return ResponseEntity.ok(adminDashboardService.getOrderSummary());
    }

    @GetMapping("/inventory/summary")
    public ResponseEntity<InventorySummaryDTO> getInventorySummary() {
        return ResponseEntity.ok(adminDashboardService.getInventorySummary());
    }
}
