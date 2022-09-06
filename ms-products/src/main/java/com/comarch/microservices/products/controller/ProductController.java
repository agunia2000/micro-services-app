package com.comarch.microservices.products.controller;

import com.comarch.microservices.products.request.ProductRequest;
import com.comarch.microservices.products.response.ProductResponse;
import com.comarch.microservices.products.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        log.info("New product was added " + productRequest);
        return ResponseEntity.ok(new ProductResponse(productRequest.getCode(), productRequest.getName(),
                productRequest.getDescription(), productRequest.getPrice()));
    }
}
