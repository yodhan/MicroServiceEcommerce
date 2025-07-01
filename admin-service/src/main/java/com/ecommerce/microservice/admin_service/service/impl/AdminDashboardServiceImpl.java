package com.ecommerce.microservice.admin_service.service.impl;

import com.ecommerce.microservice.admin_service.dto.*;
import com.ecommerce.microservice.admin_service.service.AdminDashboardService;
import com.ecommerce.microservice.admin_service.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final AdminUserService adminUserService;
    private final RestClient restClient = RestClient.builder().build();

    private static final String ORDER_SERVICE_BASE_URL = "http://ORDER-SERVICE/api/orders";
    private static final String INVENTORY_SERVICE_BASE_URL = "http://INVENTORY-SERVICE/api/inventory";

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return adminUserService.getAllUsers();
    }

    @Override
    public void updateUserStatus(String userId, boolean enabled) {
        adminUserService.updateUserStatus(userId, enabled);
    }

    @Override
    public void deleteUser(String userId) {
        adminUserService.deleteUser(userId);
    }

    @Override
    public OrderSummaryDTO getOrderSummary() {
        try {
            return restClient.get()
                    .uri(ORDER_SERVICE_BASE_URL + "/summary")
                    .retrieve()
                    .body(OrderSummaryDTO.class);
        } catch (Exception e) {
            return new OrderSummaryDTO(0, BigDecimal.ZERO);
        }
    }

    @Override
    public InventorySummaryDTO getInventorySummary() {
        try {
            return restClient.get()
                    .uri(INVENTORY_SERVICE_BASE_URL + "/summary")
                    .retrieve()
                    .body(InventorySummaryDTO.class);
        } catch (Exception e) {
            return new InventorySummaryDTO(0, 0);
        }
    }
}
