package com.ecommerce.microservice.product_service.service;

import com.ecommerce.microservice.product_service.dto.ProductRequest;
import com.ecommerce.microservice.product_service.dto.ProductResponse;
import com.ecommerce.microservice.product_service.exception.ProductNotFoundException;
import com.ecommerce.microservice.product_service.factory.ProductDTOFactory;
import com.ecommerce.microservice.product_service.model.Product;
import com.ecommerce.microservice.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOFactory productDTOFactory;

    public ProductResponse createProduct(ProductRequest productRequest){
        Product product=productDTOFactory.toEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        log.info("Product save successfully with id:{}",savedProduct.getId());
        return productDTOFactory.toResponse(savedProduct);
    }

    public List<ProductResponse> getAllProducts(){
        return  productRepository.findAll()
                .stream()
                .map(productDTOFactory::toResponse)
                .toList();
    }

    public  ProductResponse getProductById(String id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found with id:"+id));
        return productDTOFactory.toResponse(product);
    }

    public void deleteProduct(String id){
        if(!productRepository.existsById(id)){
            throw  new ProductNotFoundException("Product not found in DataBase with id:"+id);
        }
        productRepository.deleteById(id);
        log.info("Product deleted with id: {}", id);
    }

    public ProductResponse updateProduct(String id, ProductRequest updatedProduct){
        Product product =productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found with id:"+id));
        product.setName(updatedProduct.name());
        product.setDescription(updatedProduct.description());
        product.setPrice(updatedProduct.price());
        product.setUpdatedAt(updatedProduct.updatedAt());
        product.setCategory(updatedProduct.category());

        productRepository.save(product);
        log.info("Product updated: {}", id);

        return productDTOFactory.toResponse(product);

    }

}
