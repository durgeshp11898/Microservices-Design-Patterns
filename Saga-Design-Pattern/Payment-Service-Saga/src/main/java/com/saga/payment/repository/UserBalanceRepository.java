package com.saga.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saga.payment.entity.UserBalance;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance,Integer> {
	
}