package com.comarch.microservices.transactions.repository;

import com.comarch.microservices.transactions.model.TransactionItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionItemsRepository extends CrudRepository <TransactionItems, Long>{
}
