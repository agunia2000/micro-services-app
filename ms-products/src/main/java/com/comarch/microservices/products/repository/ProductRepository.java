package com.comarch.microservices.products.repository;

import com.comarch.microservices.products.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository <Product, Long>{
    List<Product> findAll();
}
