package com.ecommerce.microservice.cart_service.service;

import com.ecommerce.microservice.cart_service.model.Cart;
import com.ecommerce.microservice.cart_service.model.RedisCart;
import com.ecommerce.microservice.cart_service.repository.CartRepository;
import com.ecommerce.microservice.cart_service.repository.RedisCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final RedisCartRepository redisCartRepository;
    private final CartRepository cartRepository;

    public RedisCart getCart(String userId){
        return  redisCartRepository.findById(userId)
                .orElseGet(()->{
                    Optional<Cart> cartDb= cartRepository.findById(userId);
                    if (cartDb.isPresent()){
                        RedisCart redisCart= RedisCart.builder()
                                .userId(userId)
                                .items(cartDb.get().getItems())
                                .build();
                        redisCartRepository.save(redisCart);
                        return redisCart;
                    }
                    else {
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

    public void syncToDB(String userId){
        RedisCart redisCart= getCart(userId);
        Cart cart=new Cart();
        cart.setUserId(userId);
        cart.setItems(redisCart.getItems());
        cartRepository.save(cart);
    }
}
