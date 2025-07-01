package com.ecommerce.microservice.user_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private String keycloakId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isActive;
}
