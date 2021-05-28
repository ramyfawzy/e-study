package com.home.estudy.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		OpenAPI openApi = new OpenAPI();
		Components components = new Components();
		Info info = new Info();
		info.title("Contact Application API")
				.description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
				.license(new License().name("GNU public").url("https://license.com")).version("1.0");
		openApi.components(components);
		openApi.info(info);
		return openApi;
	}
}
