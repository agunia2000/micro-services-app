package com.comarch.microservices.products.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequest {
    private String code;
    private String name;
    private String description;
    private double price;
}
