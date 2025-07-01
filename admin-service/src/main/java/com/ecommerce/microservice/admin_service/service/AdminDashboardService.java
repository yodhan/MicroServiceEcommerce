package com.ecommerce.microservice.admin_service.service;

import com.ecommerce.microservice.admin_service.dto.OrderSummaryDTO;
import com.ecommerce.microservice.admin_service.dto.InventorySummaryDTO;
import com.ecommerce.microservice.admin_service.dto.UserResponseDTO;

import java.util.List;

public interface AdminDashboardService {
    List<UserResponseDTO> getAllUsers();
    void updateUserStatus(String userId, boolean enabled);
    void deleteUser(String userId);
    OrderSummaryDTO getOrderSummary();
    InventorySummaryDTO getInventorySummary();
}
