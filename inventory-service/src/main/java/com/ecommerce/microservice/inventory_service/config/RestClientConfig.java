package com.ecommerce.microservice.inventory_service.config;

import com.ecommerce.microservice.inventory_service.client.InventoryClient;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${inven}")
    private String inventoryClientUrl;
    @Bean
    public InventoryClient inventoryClient()
    {
        RestClient restClient= RestClient.builder()
                .baseUrl(in)
                .build();
        var restClientAdapter= RestClientAdapter.create(restClient);
        var httpServiceProxyFactory= HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }
}
