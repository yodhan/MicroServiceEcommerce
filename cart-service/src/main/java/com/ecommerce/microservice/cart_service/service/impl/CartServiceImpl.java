package com.ecommerce.microservice.cart_service.service.impl;

import com.ecommerce.microservice.cart_service.model.Cart;
import com.ecommerce.microservice.cart_service.model.CartItem;
import com.ecommerce.microservice.cart_service.model.RedisCart;
import com.ecommerce.microservice.cart_service.repository.CartRepository;
import com.ecommerce.microservice.cart_service.repository.RedisCartRepository;
import com.ecommerce.microservice.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final RedisCartRepository redisCartRepository;
    private final CartRepository cartRepository;

    public RedisCart getCart(String userId){
        return redisCartRepository.findById(userId)
                .orElseGet(() -> {
                    Optional<Cart> cartDb = cartRepository.findById(userId);
                    if (cartDb.isPresent()) {
                        // Convert List<CartItem> to Map<String, Integer>
                        Map<String, Integer> itemMap = new HashMap<>();
                        for (CartItem item : cartDb.get().getItems()) {
                            itemMap.put(item.getProductId(), item.getQuantity());
                        }
                        RedisCart redisCart = RedisCart.builder()
                                .userId(userId)
                                .items(itemMap)
                                .build();
                        redisCartRepository.save(redisCart);
                        return redisCart;
                    } else {
                        return new RedisCart(userId, new HashMap<>());
                    }
                });

        /*
        .items(cartDb.get().getItems()) can be written as
        .items(cartDb.map(Cart::getItems).orElse(new HashMap<>()))

        return cartRepo.findById(userId)
        .map(cart -> {
            RedisCart redisCart = RedisCart.builder()
                    .userId(userId)
                    .items(cart.getItems())
                    .build();
            redisCartRepo.save(redisCart);
            return redisCart;
        })
        .orElse(new RedisCart(userId, new HashMap<>()));


         */
    }

    public void addToCart(String userId, String productId, int quantity){
        RedisCart cart =getCart(userId);
        Map<String ,Integer> items=  cart.getItems();
        items.put(productId, items.getOrDefault(productId,0)+quantity);
          /*
            Map<String, Integer> items = new HashMap<>(cart.getItems());
            items.put(...);
            cart.setItems(items);
         */
        redisCartRepository.save(cart);
    }

//    public void syncToDB(String userId){
//        RedisCart redisCart= getCart(userId);
//        Cart cart=new Cart();
//        cart.setUserId(userId);
//        cart.setItems(redisCart.getItems());
//        cartRepository.save(cart);
//    }
    public void syncToDB(String userId) {
    RedisCart redisCart = getCart(userId);

    List<CartItem> items = new ArrayList<>();
    redisCart.getItems().forEach((productId, quantity) -> {
        CartItem item = new CartItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        items.add(item);
    });

    Cart cart = new Cart();
    cart.setUserId(userId);
    cart.setLastUpdated(LocalDateTime.now());

    // Set back-reference in each item
    for (CartItem item : items) {
        item.setCart(cart);
    }

    cart.setItems(items);

    cartRepository.save(cart);
}


    public void removeItem(String userId, String productId) {
        // Redis
        RedisCart redisCart = redisCartRepository.findById(userId).orElse(null);
        if (redisCart != null) {
            redisCart.getItems().remove(productId);
            redisCartRepository.save(redisCart);
        }

        // DB
        Optional<Cart> optionalCart = cartRepository.findById(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.getItems().removeIf(item -> item.getProductId().equals(productId));
            cartRepository.save(cart);  // Thanks to orphanRemoval=true, item will be deleted
        }
    }


//    public void removeItem(String userId, String productId) {
//        // Redis
//        RedisCart redisCart = redisCartRepository.findById(userId).orElse(null);
//        if (redisCart != null && redisCart.getItems() != null) {
//            redisCart.getItems().remove(productId);
//            redisCartRepository.save(redisCart);
//        }
//
//        // DB
//        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
//        if (cart != null) {
//            cartRepository.delete(cart);
//        }
//    }


    public void clearCart(String userId) {
        // Redis
        redisCartRepository.deleteById(userId);
        // DB
        cartRepository.deleteByUserId(userId);
    }

}
