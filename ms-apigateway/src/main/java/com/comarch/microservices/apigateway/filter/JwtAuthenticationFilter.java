package com.comarch.microservices.apigateway.filter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import com.comarch.microservices.apigateway.client.CustomerClient;
import com.comarch.microservices.apigateway.exception.JwtTokenMalformedException;
import com.comarch.microservices.apigateway.exception.JwtTokenMissingException;
import com.comarch.microservices.apigateway.response.CustomerResponse;
import com.comarch.microservices.apigateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GatewayFilter {

    private final JwtUtil jwtUtil;

    // private final CustomerClient customerClient; -> makes dependency cycle

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = Arrays.asList("/register", "/login");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().equals(uri)); // org: contains instead of equals

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return response.setComplete();
            }

            String token = request.getHeaders().getOrEmpty("Authorization").get(0);
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            try {
               //  String email = jwtUtil.getClaims(token).getSubject();
               // CustomerResponse customer = customerClient.getCustomerByEmail(email);
               //  if (customer != null) {
                jwtUtil.validateToken(token);
                exchange.getResponse().getHeaders().put("email", Collections.singletonList(jwtUtil.getClaims(token).getSubject()));
               // } else {
               //     throw new JwtTokenMalformedException("User not found!");
               // }
            } catch (JwtTokenMalformedException | JwtTokenMissingException e) {
                e.printStackTrace();

                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return response.setComplete();
            }

            Claims claims = jwtUtil.getClaims(token);
            exchange.getRequest().mutate().header("email", String.valueOf(claims.get("email"))).build();
        }

        return chain.filter(exchange);
    }

}