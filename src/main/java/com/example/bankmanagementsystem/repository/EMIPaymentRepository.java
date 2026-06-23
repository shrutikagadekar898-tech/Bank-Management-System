package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.EMIPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EMIPaymentRepository
        extends JpaRepository<EMIPayment, Long> {

    List<EMIPayment> findByUsername(String username);
    List<EMIPayment> findByLoanId(Long loanId);

}