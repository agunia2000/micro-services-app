package com.comarch.microservices.transactions.service;

import com.comarch.microservices.transactions.clients.CustomerClient;
import com.comarch.microservices.transactions.clients.ProductClient;
import com.comarch.microservices.transactions.model.Transaction;
import com.comarch.microservices.transactions.model.TransactionItems;
import com.comarch.microservices.transactions.repository.TransactionRepository;
import com.comarch.microservices.transactions.response.TransactionResponse;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final CustomerClient customerClient;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private double getTransactionValue(List<String> productCodeList) {
        return productCodeList.stream().mapToDouble(x -> productClient.getProductByCode(x).getPrice()).sum();
    }

    private boolean productsExist(List<String> transactionsProductCodes) {
        return transactionsProductCodes.stream().noneMatch(x -> productClient.getProductByCode(x) == null);
    }

    private Transaction prepareTransaction(List<String> transactionProductCodes, String email) {
        Transaction transaction = new Transaction();
        transaction.setCustomerId(customerClient.getCustomerByEmail(email).getId());
        transaction.setTransactionValue(getTransactionValue(transactionProductCodes));
        transaction.setDate(new Date());
        transactionProductCodes.forEach(x -> transaction.addItem(new TransactionItems(productClient.getProductByCode(x).getId())));

        return transaction;
    }

    private TransactionResponse mapTransactionResponseBody(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionId(transaction.getId())
                .customerId(transaction.getCustomerId())
                .transactionDate(transaction.getDate())
                .transactionValue(transaction.getTransactionValue())
                .productsId(transaction.getTransactionItems().stream().mapToLong(TransactionItems::getProductId).boxed().collect(Collectors.toList()))
                .build();
    }


    public TransactionResponse addTransaction(List<String> transactionProductCodes, String email) {
        if (productsExist(transactionProductCodes)) {
            Transaction transaction = prepareTransaction(transactionProductCodes, email);
            transactionRepository.save(transaction);
            return mapTransactionResponseBody(transaction);
        }
        return new TransactionResponse();
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

    public String getEmail(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }
}
