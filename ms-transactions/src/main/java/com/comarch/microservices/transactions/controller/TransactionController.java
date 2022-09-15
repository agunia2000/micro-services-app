package com.comarch.microservices.transactions.controller;

import com.comarch.microservices.transactions.request.TransactionRequest;
import com.comarch.microservices.transactions.response.TransactionResponse;
import com.comarch.microservices.transactions.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> addTransaction(@RequestHeader("Authorization") String token, @RequestBody TransactionRequest request){
        request.getProductCodeList().replaceAll(String::toUpperCase);

        return new ResponseEntity<>(transactionService.addTransaction(request.getProductCodeList(),transactionService.getEmail(token)), HttpStatus.CREATED);
    }
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(){
        return new ResponseEntity<>(transactionService.getTransactions(),HttpStatus.OK);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long id){
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>("Transaction With Id " + id + " Was Successfully Deleted",HttpStatus.OK);
    }

}
