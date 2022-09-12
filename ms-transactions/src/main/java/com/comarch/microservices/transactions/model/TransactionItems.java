package com.comarch.microservices.transactions.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTION_ITEMS")
public class TransactionItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private long productId;
    @ManyToOne
    private Transaction transaction;

    public TransactionItems(Long productId) {
        this.productId = productId;
    }
}
