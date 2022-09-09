package com.comarch.microservices.products.controller;

import com.comarch.microservices.products.model.Product;
import com.comarch.microservices.products.request.ProductRequest;
import com.comarch.microservices.products.response.ProductResponse;
import com.comarch.microservices.products.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/products")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request){

        productService.addProduct(request);
        log.info("New product with code '" + request.getCode() +  "' was added");

        return ResponseEntity.ok(new ProductResponse(request.getCode(), request.getName(),
                request.getDescription(), request.getPrice()));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return "Successfully Deleted Product with Id: " + productId;
    }

    @GetMapping("/products/{code}")
    public ProductResponse getProductByCode(@PathVariable("code") String itemCode){
        return productService.getProduct(itemCode);
    }
}
