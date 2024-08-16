package com.paymybuddy.repository;

import com.paymybuddy.entity.AccountEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {

    @Query("SELECT a FROM AccountEntity a JOIN a.userEntity u WHERE u.username = :username")
    Optional<AccountEntity> findByUsername(@Param("username") String username);

}
