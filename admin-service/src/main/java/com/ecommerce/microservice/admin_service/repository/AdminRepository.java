package com.ecommerce.microservice.admin_service.repository;

import com.ecommerce.microservice.admin_service.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
