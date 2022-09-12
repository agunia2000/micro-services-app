package com.comarch.microservices.transactions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long customerId;
    @Column(updatable = false)
    private Date date;
    @NotNull
    private double transactionValue;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<TransactionItems> transactionItems = new ArrayList<>();
    public void addItem(TransactionItems transactionItem){
        transactionItems.add(transactionItem);
        transactionItem.setTransaction(this);
    }
}
