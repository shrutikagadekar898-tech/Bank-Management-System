package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.dto.DashboardResponse;
import com.example.bankmanagementsystem.repository.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;
    private final LoanRepository loanRepository;
    private final TransactionRepository transactionRepository;

    public DashboardController(
            UserRepository userRepository,
            AccountRepository accountRepository,
            BankRepository bankRepository,
            LoanRepository loanRepository,
            TransactionRepository transactionRepository) {

        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
        this.loanRepository = loanRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public DashboardResponse getDashboard() {

        return new DashboardResponse(
                userRepository.count(),
                accountRepository.count(),
                bankRepository.count(),
                loanRepository.count(),
                transactionRepository.count()
        );
    }
}