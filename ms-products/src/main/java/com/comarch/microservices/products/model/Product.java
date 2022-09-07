package com.comarch.microservices.products.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCTS",uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private double price;
}
