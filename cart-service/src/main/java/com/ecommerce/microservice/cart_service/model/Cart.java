package com.ecommerce.microservice.cart_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private String userId;

    //will have two table here
    @ElementCollection
    @CollectionTable(name="cart_items", joinColumns=@JoinColumn(name = "user-id"))
    @MapKeyColumn(name="product_id")
    @Column(name="quantity")
    private Map<String, Integer> items;

    private LocalDateTime lastUpdated;
}
