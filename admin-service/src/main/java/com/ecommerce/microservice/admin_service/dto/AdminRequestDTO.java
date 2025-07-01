package com.ecommerce.microservice.admin_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRequestDTO {
    private String username;
    private String email;
    private String role;
}
