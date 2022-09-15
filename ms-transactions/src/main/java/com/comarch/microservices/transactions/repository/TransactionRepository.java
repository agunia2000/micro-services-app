package com.comarch.microservices.transactions.repository;

import com.comarch.microservices.transactions.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository <Transaction, Long>{
    List<Transaction> findAll();
}
