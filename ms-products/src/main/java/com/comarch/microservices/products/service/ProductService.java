package com.comarch.microservices.products.service;

import com.comarch.microservices.products.model.Product;
import com.comarch.microservices.products.request.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public void addProduct(ProductRequest request){
        Product product = Product.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }
}
