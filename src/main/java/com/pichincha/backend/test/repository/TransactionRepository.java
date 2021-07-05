package com.pichincha.backend.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pichincha.backend.test.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	List<Transaction> findAllByIdAccountOrderByCreationDateDesc(Long idAccount);
	
	List<Transaction> findAllByIdAccountAndTypeOrderByCreationDateAsc(Long idAccount, String type);

}
