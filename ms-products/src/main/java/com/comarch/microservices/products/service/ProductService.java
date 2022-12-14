package com.comarch.microservices.products.service;

import com.comarch.microservices.products.model.Product;
import com.comarch.microservices.products.repository.ProductRepository;
import com.comarch.microservices.products.request.ProductRequest;
import com.comarch.microservices.products.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Long addProduct(ProductRequest request){
        Product product = Product.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        return productRepository.save(product).getId();
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public ProductResponse getProductByCode(String code){
       return productRepository.findByCode(code);
    }

}
