package com.comarch.microservices.apigateway.config;

import com.comarch.microservices.apigateway.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("register", r -> r.path("/register").filters(f -> f.filter(filter)).uri("lb://authorization"))
                .route("login", r -> r.path("/login").filters(f -> f.filter(filter)).uri("lb://authorization"))
                .route("products", r -> r.path("/products/**").filters(f -> f.filter(filter)).uri("lb://ms-products")).build();
    }

}