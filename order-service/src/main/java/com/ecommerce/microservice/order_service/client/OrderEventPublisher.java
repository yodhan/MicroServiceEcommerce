package com.ecommerce.microservice.order_service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void publishOrderPlaceEvent(String orderJson){
        kafkaTemplate.send("order-events", orderJson);
    }
}
