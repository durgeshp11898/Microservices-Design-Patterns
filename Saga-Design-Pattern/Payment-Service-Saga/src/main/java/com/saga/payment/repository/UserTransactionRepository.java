package com.saga.payment.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.saga.payment.entity.UserTransaction;


public interface UserTransactionRepository extends JpaRepository<UserTransaction,Integer> {
}
