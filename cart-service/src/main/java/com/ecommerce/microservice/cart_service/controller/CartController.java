package com.ecommerce.microservice.cart_service.controller;

import com.ecommerce.microservice.cart_service.model.RedisCart;
import com.ecommerce.microservice.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<RedisCart> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<Void> addToCart(@PathVariable String userId,
                                          @RequestParam String productId,
                                          @RequestParam int quantity) {
        cartService.addToCart(userId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/sync")
    public ResponseEntity<Void> syncToDB(@PathVariable String userId) {
        cartService.syncToDB(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/remove")
    public ResponseEntity<Void> removeItem(@PathVariable String userId,
                                           @RequestParam String productId) {
        cartService.removeItem(userId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
