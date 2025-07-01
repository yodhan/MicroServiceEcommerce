package com.ecommerce.microservice.admin_service.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private boolean enabled;
}
