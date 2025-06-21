package com.ecommerce.microservice.ecommapp.repository;

import com.ecommerce.microservice.ecommapp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
