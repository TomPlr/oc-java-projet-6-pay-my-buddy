package com.paymybuddy.repository;

import com.paymybuddy.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}