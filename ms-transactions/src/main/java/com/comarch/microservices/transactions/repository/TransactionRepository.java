package com.comarch.microservices.transactions.repository;

import com.comarch.microservices.transactions.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository <Transaction, Long>{
}
