package com.ventura013.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenApi () {
		return new OpenAPI().info(new Info()
				.title("RESTful API with JAVA 17 and Spring Boot 3.0.4")
				.version("v1")
				.description("Some description about your API")
				.termsOfService("https://github.com/Ventura013")
				.license(new License().name("Apache 2.0").url("https://github.com/Ventura013")));	
	}
}
