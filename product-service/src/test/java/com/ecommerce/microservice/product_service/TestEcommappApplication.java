package com.ecommerce.microservice.product_service;

import org.springframework.boot.SpringApplication;

public class TestEcommappApplication {

	public static void main(String[] args) {
		SpringApplication.from(EcommappApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
