package com.comarch.microservices.transactions.service;

import com.comarch.microservices.transactions.clients.ProductClient;
import com.comarch.microservices.transactions.model.Transaction;
import com.comarch.microservices.transactions.model.TransactionItems;
import com.comarch.microservices.transactions.repository.TransactionRepository;
import com.comarch.microservices.transactions.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ProductClient productClient;

    private double getTransactionValue(List<String> productCodeList) {
        return productCodeList.stream().mapToDouble(x -> productClient.getProductByCode(x).getPrice()).sum();
    }
    private boolean productsExist(List<String> transactionsProductCodes){
      return transactionsProductCodes.stream().noneMatch(x -> productClient.getProductByCode(x) == null);
    }

    public boolean addTransaction(List<String> transactionProductCodes) {
        if(productsExist(transactionProductCodes)) {
            Transaction transaction = new Transaction();
            transaction.setTransactionValue(getTransactionValue(transactionProductCodes));
            transaction.setDate(new Date());
            transactionProductCodes.forEach(x -> transaction.addItem(new TransactionItems(productClient.getProductByCode(x).getId())));

            transactionRepository.save(transaction);
            return true;
        }
        else{ return false;}
    }

    public List<TransactionResponse> getTransactions() {
        List<TransactionResponse> transactionResponsesList = new ArrayList<>();

        transactionRepository.findAll().forEach(x -> {
            TransactionResponse response = TransactionResponse.builder()
                    .transactionId(x.getId())
                    .customerId(x.getCustomerId())
                    .transactionDate(x.getDate())
                    .transactionValue(x.getTransactionValue())
                    .productsId(x.getTransactionItems().stream().mapToLong(TransactionItems::getProductId).boxed().collect(Collectors.toList()))
                    .build();
            transactionResponsesList.add(response);
        });

        return transactionResponsesList;
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
