package com.ecommerce.microservice.inventory_service.repository;

import com.ecommerce.microservice.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
//    boolean existsBySkuCodeAndQuantityIsGreaterThanEquals(String skuCode, Integer quantity);

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
