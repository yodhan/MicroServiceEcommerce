package com.ecommerce.microservice.ecommapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class EcommappApplicationTests {

	@Test
	void contextLoads() {
	}

}
