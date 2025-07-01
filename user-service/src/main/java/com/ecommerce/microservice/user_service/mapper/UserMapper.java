package com.ecommerce.microservice.user_service.mapper;

import com.ecommerce.microservice.user_service.dto.UserRequestDTO;
import com.ecommerce.microservice.user_service.dto.UserResponseDTO;
import com.ecommerce.microservice.user_service.model.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        return User.builder()
                .keycloakId(dto.getKeycloakId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static UserResponseDTO toDto(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .keycloakId(user.getKeycloakId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
