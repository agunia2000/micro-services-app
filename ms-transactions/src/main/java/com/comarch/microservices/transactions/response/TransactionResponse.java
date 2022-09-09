package com.comarch.microservices.transactions.response;

import com.comarch.microservices.transactions.model.Transaction;
import com.comarch.microservices.transactions.model.TransactionItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    Transaction transaction;
    List<TransactionItems> transactionItemsList;
}
