package com.bernacki.abbank.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("individual-user-service",p -> p.path("/v1/users/**")
                        .uri("lb://individual-user-service"))
                .route("personal-account-service",p -> p.path("/v1/accounts/**")
                        .uri("lb://personal-account-service"))
                .build();
    }
}
