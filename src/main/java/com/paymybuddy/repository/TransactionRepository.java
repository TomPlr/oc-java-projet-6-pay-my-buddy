package com.paymybuddy.repository;

import com.paymybuddy.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {


    /**
     * Finds all TransactionEntities associated with a user by their username.
     *
     * @param username the username of the user
     *
     * @return an Iterable containing the TransactionEntities associated with the user
     */
    @Query("SELECT t FROM TransactionEntity t " +
            "JOIN t.receiverAccountEntity receiverAccount " +
            "JOIN receiverAccount.userEntity receiver " +
            "JOIN t.senderAccountEntity senderAccount " +
            "JOIN senderAccount.userEntity sender " +
            "WHERE receiver.username = :username OR sender.username = :username")
    Iterable<TransactionEntity> findAllByUsername(String username);
}
