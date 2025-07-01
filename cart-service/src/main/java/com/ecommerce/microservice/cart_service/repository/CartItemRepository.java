package com.ecommerce.microservice.cart_service.repository;

import com.ecommerce.microservice.cart_service.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    Optional<CartItem> findByCartUserIdAndProductId(String userId, String productId);
}
