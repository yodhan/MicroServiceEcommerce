package com.ecommerce.microservice.cart_service.repository;

import com.ecommerce.microservice.cart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
