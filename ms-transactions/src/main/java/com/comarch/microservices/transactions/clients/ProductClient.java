package com.comarch.microservices.transactions.clients;

import com.comarch.microservices.transactions.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ProductWebService",url = "lb://ms-products")
public interface ProductClient {
    @GetMapping("/products/{code}")
    //public List<ProductResponse> getProducts();
    public ProductResponse getProductByCode(@PathVariable("code") String code);
}
