package com.comarch.microservices.transactions.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class TransactionResponse {
    long transactionId;
    long customerId;
    @Temporal(TemporalType.TIMESTAMP)
    Date transactionDate;
    double transactionValue;
    List<Long> productsId;
}
