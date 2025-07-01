package com.ecommerce.microservice.product_service.factory;


import com.ecommerce.microservice.product_service.dto.ProductRequest;
import com.ecommerce.microservice.product_service.dto.ProductResponse;
import com.ecommerce.microservice.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOFactory {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .category(request.category())
                .brand(request.brand())
                .imageUrl(request.imageUrl())
                .stockQuantity(request.stockQuantity())
                .isActive(request.isActive() != null ? request.isActive() : true)
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.getBrand(),
                product.getImageUrl(),
                product.getStockQuantity(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.isActive()
        );
    }
}
