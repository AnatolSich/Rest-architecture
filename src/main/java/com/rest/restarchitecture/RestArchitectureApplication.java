package com.rest.restarchitecture;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Events API",
				description = "API Definitions of the Events Microservice",
				version = "3.0.1"

		))
public class RestArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestArchitectureApplication.class, args);
	}

}
