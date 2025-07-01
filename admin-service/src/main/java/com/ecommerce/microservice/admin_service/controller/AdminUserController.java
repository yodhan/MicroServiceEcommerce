package com.ecommerce.microservice.admin_service.controller;

import com.ecommerce.microservice.admin_service.dto.UserResponseDTO;
import com.ecommerce.microservice.admin_service.service.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Admin User Management", description = "Endpoints for managing application users by admin")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a list of all registered users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(adminUserService.getAllUsers());
    }

    @PutMapping("/{userId}/status")
    @Operation(summary = "Update user status", description = "Enable or disable a user account")
    public ResponseEntity<Void> updateUserStatus(
            @PathVariable String userId,
            @RequestParam boolean enabled
    ) {
        adminUserService.updateUserStatus(userId, enabled);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user", description = "Permanently delete a user by their ID")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        adminUserService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
