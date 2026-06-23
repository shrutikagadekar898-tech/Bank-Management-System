package com.example.bankmanagementsystem.repository;
import java.util.List;
//import com.example.bankmanagementsystem.entity.Loan;
import com.example.bankmanagementsystem.entity.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRequestRepository
        extends JpaRepository<LoanRequest, Long> {
    List<LoanRequest>findByStatus(String status);
    List<LoanRequest> findByUsername(String username);
}