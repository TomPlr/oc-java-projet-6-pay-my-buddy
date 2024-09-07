package com.paymybuddy.repository;

import com.paymybuddy.entity.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {

    /**
     * Finds an AccountEntity by the associated user's username.
     *
     * @param username the username of the user
     *
     * @return an Optional containing the AccountEntity if found, otherwise an empty Optional
     */
    @Query("SELECT a FROM AccountEntity a JOIN a.userEntity u WHERE u.username = :username")
    Optional<AccountEntity> findByUsername(@Param("username") String username);

}
