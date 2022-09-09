package com.comarch.microservices.transactions.controller;

import com.comarch.microservices.transactions.request.TransactionRequest;
import com.comarch.microservices.transactions.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    /*
    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> addTransaction(@RequestBody TransactionRequest request){
        double transactionValue = transactionService.getTransactionValue(request.getProductCodeList());

        return ResponseEntity.ok(new TransactionResponse(request.getProductCodeList()));
    }
    */

    @GetMapping("/transactions")
    public double getTransactionValue(@RequestBody TransactionRequest request){
        return transactionService.getTransactionValue(request.getProductCodeList());
    }

}
