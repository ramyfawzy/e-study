package com.home.estudy.spring.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		OpenAPI openApi = new OpenAPI();
//		Components components = new Components();
		Info info = new Info();
		info.title("Contact Application API")
				.description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
				.license(new License().name("GNU public").url("https://license.com")).version("1.0");
		OAuthFlows flows = new OAuthFlows();
		OAuthFlow flow = new OAuthFlow();
		flow.setAuthorizationUrl("http://localhost:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/auth");
		flow.setTokenUrl("http://localhost:8180/auth/realms/SpringBootKeycloak/protocol/openid-connect/token");
		Scopes scopes = new Scopes();
		flow.setScopes(scopes);
		flows = flows.implicit(flow);
		openApi.components(new Components().addSecuritySchemes("keycloak",
				new SecurityScheme().type(SecurityScheme.Type.OAUTH2).flows(flows)))
				.addSecurityItem(new SecurityRequirement().addList("keycloak", Arrays.asList("read", "write")));
//		openApi.components(components);
		openApi.info(info);
		return openApi;
	}
}
