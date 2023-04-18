package com.jobaaj.mapping.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobaaj.mapping.entity.Payment;


public interface PaymentRepo extends JpaRepository<Payment, Integer> {
	
	

}
