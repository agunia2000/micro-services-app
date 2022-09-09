package com.comarch.microservices.transactions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    //TODO: zrobic notNullem ale tak zeby z logoawania bralo idcustomera przy transakcji
    private long customerId;
    @Column(columnDefinition = "TIMESTAMP")
    private Date date;
    @NotNull
    private double transactionValue;
}
