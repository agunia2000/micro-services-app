package com.comarch.microservices.products.controller;

import com.comarch.microservices.products.model.Product;
import com.comarch.microservices.products.request.ProductRequest;
import com.comarch.microservices.products.response.ProductResponse;
import com.comarch.microservices.products.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {

        Long productId = productService.addProduct(request);
        log.info("New product with code '" + request.getCode() + "' was added");

        return new ResponseEntity<>(new ProductResponse(productId, request.getCode(), request.getName(),
                request.getDescription(), request.getPrice()), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Successfully Deleted Product with Id: " + productId,HttpStatus.OK);
    }

    @GetMapping("/products/{code}")
    public ResponseEntity<ProductResponse> getProductByCode(@PathVariable("code") String itemCode) {
        return new ResponseEntity<>(productService.getProductByCode(itemCode),HttpStatus.OK);
    }
}
