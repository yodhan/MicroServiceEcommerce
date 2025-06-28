package com.ecommerce.microservice.cart_service.repository;

import com.ecommerce.microservice.cart_service.model.RedisCart;
import org.springframework.data.repository.CrudRepository;

public interface RedisCartRepository extends CrudRepository<RedisCart, String> {
    //Crudrepo is uised for redis
}
