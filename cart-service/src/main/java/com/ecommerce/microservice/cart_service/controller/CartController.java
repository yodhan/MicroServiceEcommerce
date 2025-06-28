package com.ecommerce.microservice.cart_service.controller;

import com.ecommerce.microservice.cart_service.model.RedisCart;
import com.ecommerce.microservice.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;


    @GetMapping("/{userId}")
    public ResponseEntity<RedisCart> getCart(@PathVariable String userId){
        RedisCart redisCart=cartService.getCart((userId));
        return ResponseEntity.ok(redisCart);
    }

    @PostMapping("/{userId}/add")
    public  ResponseEntity<String> addToCart(@PathVariable String userId,
                                             @RequestParam String productId,
                                             @RequestParam int quantity) {
        cartService.addToCart(userId,productId,quantity);
        return ResponseEntity.ok("Product added to cart.");
    }

    @PostMapping("/{userId}/sync")
    public ResponseEntity<String> syncCart(@PathVariable String userId) {
        cartService.syncToDB(userId);
        return ResponseEntity.ok("Cart synced to database.");
    }

}
