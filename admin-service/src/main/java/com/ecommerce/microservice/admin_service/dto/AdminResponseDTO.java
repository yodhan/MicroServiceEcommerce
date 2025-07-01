package com.ecommerce.microservice.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String role;
    private Boolean isActive;
}
