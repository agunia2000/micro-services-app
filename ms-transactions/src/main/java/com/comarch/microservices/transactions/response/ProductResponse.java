package com.comarch.microservices.transactions.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private long id;
    private String code;
    private String name;
    private String description;
    private double price;
}
