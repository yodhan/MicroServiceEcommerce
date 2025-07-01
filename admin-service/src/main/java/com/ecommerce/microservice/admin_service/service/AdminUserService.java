package com.ecommerce.microservice.admin_service.service;

import com.ecommerce.microservice.admin_service.dto.UserResponseDTO;

import java.util.List;

public interface AdminUserService {
    List<UserResponseDTO> getAllUsers();
    void updateUserStatus(String userId, boolean enabled);
    void deleteUser(String userId);
}
