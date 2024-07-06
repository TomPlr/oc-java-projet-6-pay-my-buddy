package com.paymybuddy.repository;

import com.paymybuddy.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
}
