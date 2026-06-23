package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {
    Long countByUsername(String username);
    List<Transaction> findByAccountNumberOrderByTransactionDateDesc(String accountNumber);
List<Transaction> findByAccountNumber(String accountNumber);
    List<Transaction> findByUsername(String username);}