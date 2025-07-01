package com.ecommerce.microservice.cart_service.service;

import com.ecommerce.microservice.cart_service.dto.CartDTO;
import com.ecommerce.microservice.cart_service.dto.CartItemDTO;
import com.ecommerce.microservice.cart_service.model.RedisCart;

public interface CartService {

    RedisCart getCart(String userId);

    void removeItem(String userId, String productId);

    void clearCart(String userId);

    public void addToCart(String userId, String productId, int quantity);

    void syncToDB(String userId);
}
