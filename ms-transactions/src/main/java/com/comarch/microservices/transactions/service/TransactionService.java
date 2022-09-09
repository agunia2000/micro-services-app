package com.comarch.microservices.transactions.service;

import com.comarch.microservices.transactions.clients.ProductClient;
import com.comarch.microservices.transactions.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ProductClient productClient;

    public double getTransactionValue(List<String> productCodeList){
        double sumValue = 0;

        for(String productCode : productCodeList){
            sumValue += productClient.getProductByCode(productCode).getPrice();
        }
        return sumValue;
    }
}
