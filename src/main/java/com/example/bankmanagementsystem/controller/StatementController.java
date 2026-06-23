package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.entity.Transaction;
import com.example.bankmanagementsystem.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statements")
@CrossOrigin("*")
public class StatementController {

    private final TransactionRepository transactionRepository;

    public StatementController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/{accountNumber}")
    public List<Transaction> getStatement(
            @PathVariable String accountNumber) {

        return transactionRepository
                .findByAccountNumberOrderByTransactionDateDesc(accountNumber);
    }
}