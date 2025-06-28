package com.ecommerce.microservice.cart_service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("cart") //will store object under redis key like cart::userid
public class RedisCart implements Serializable {
    @Id
    private String userId;
    private Map<String, Integer> items;


}
