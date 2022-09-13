package com.comarch.microservices.transactions.controller;

import com.comarch.microservices.transactions.request.TransactionRequest;
import com.comarch.microservices.transactions.response.TransactionResponse;
import com.comarch.microservices.transactions.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping("/transactions")
    public String addTransaction(@RequestBody TransactionRequest request){
        request.getProductCodeList().replaceAll(String::toUpperCase);

        if(transactionService.addTransaction(request.getProductCodeList())){
            return "Transaction Was Added!";
        }
        return "Error while creating transaction! One or more products are missing in stock.";
    }
    @GetMapping("/transactions")
    public List<TransactionResponse> getTransactions(){
        return transactionService.getTransactions();
    }

    @DeleteMapping("/transactions/{id}")
    public String deleteTransaction(@PathVariable("id") Long id){
        transactionService.deleteTransaction(id);

        return "Transaction With Id " + id + " Was Successfully Deleted";
    }

}
