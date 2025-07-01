package com.ecommerce.microservice.admin_service.mapper;

import com.ecommerce.microservice.admin_service.dto.AdminRequestDTO;
import com.ecommerce.microservice.admin_service.dto.AdminResponseDTO;
import com.ecommerce.microservice.admin_service.model.Admin;

public class AdminMapper {

    public static Admin toEntity(AdminRequestDTO dto) {
        return Admin.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .role(dto.getRole())
                .isActive(true)
                .build();
    }

    public static AdminResponseDTO toDto(Admin admin) {
        return AdminResponseDTO.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .email(admin.getEmail())
                .role(admin.getRole())
                .isActive(admin.getIsActive())
                .build();
    }
}
