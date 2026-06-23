package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository
        extends JpaRepository<Loan, Long> {
    Long countByUsername(String username);
    List<Loan> findByAccountNumber(String accountNumber);
    List<Loan>findByStatus(String Status);
    List<Loan> findByUsername(String username);

}