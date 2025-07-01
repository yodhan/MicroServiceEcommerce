package com.ecommerce.microservice.order_service.repository;

import com.ecommerce.microservice.order_service.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
