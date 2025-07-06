package com.ecommerce.microservice.gateway.route;

import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;


@Configuration
public class Route {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        // Temporarily disabled circuit breaker for debugging
        return route("product_service")
                .route(RequestPredicates.path("/api/product/**"), HandlerFunctions.http("lb://product-service"))
                // .filter(circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute"))) // Commented out
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute() {
        return route("order_service")
                .route(RequestPredicates.path("/api/order/**"), HandlerFunctions.http("lb://order-service"))
                .filter(setPath("/api/order"))
                .filter(circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute() {
        return route("inventory_service")
                .route(RequestPredicates.path("/api/inventory/**"), HandlerFunctions.http("lb://inventory-service"))
                .filter(setPath("/api/inventory"))
                .filter(circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> cartServiceRoute() {
        return route("cart_service")
                .route(RequestPredicates.path("/api/cart/**"), HandlerFunctions.http("lb://cart-service"))
                .filter(setPath("/api/cart"))
                .filter(circuitBreaker("cartServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    // Swagger Aggregation Routes
    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
        return route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("lb://product-service"))
                .filter(setPath("/v3/api-docs"))
                .filter(circuitBreaker("productServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
        return route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("lb://order-service"))
                .filter(setPath("/v3/api-docs"))
                .filter(circuitBreaker("orderServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
        return route("inventory_service_swagger")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("lb://inventory-service"))
                .filter(setPath("/v3/api-docs"))
                .filter(circuitBreaker("inventoryServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> cartServiceSwaggerRoute() {
        return route("cart_service_swagger")
                .route(RequestPredicates.path("/aggregate/cart-service/v3/api-docs"), HandlerFunctions.http("lb://cart-service"))
                .filter(setPath("/v3/api-docs"))
                .filter(circuitBreaker("cartServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    // Fallback route - Now returns JSON
    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return RouterFunctions.route(RequestPredicates.path("/fallbackRoute"),
                req -> {
                    Map<String, String> errorResponse = new HashMap<>();
                    errorResponse.put("status", "503 SERVICE_UNAVAILABLE");
                    errorResponse.put("message", "Service temporarily unavailable. Please try again later.");
                    errorResponse.put("timestamp", String.valueOf(System.currentTimeMillis()));
                    return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(errorResponse);
                });
    }

    @Bean
    public RouterFunction<ServerResponse> adminServiceRoute() {
        return route("admin_service")
                .route(RequestPredicates.path("/api/admin/**"), HandlerFunctions.http("lb://admin-service"))
                .filter(setPath("/api/admin"))
                .filter(circuitBreaker("adminServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> adminServiceSwaggerRoute() {
        return route("admin_service_swagger")
                .route(RequestPredicates.path("/aggregate/admin-service/v3/api-docs"), HandlerFunctions.http("lb://admin-service"))
                .filter(setPath("/v3/api-docs"))
                .filter(circuitBreaker("adminServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }
}
