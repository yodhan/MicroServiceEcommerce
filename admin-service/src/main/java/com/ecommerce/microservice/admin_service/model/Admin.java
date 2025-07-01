package com.ecommerce.microservice.admin_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String role;  // e.g., SUPER_ADMIN, PRODUCT_MANAGER, etc.

    private Boolean isActive;

    private String createdBy;

    private String updatedBy;
}
