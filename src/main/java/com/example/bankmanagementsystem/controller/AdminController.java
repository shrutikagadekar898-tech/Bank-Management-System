package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.dto.AdminDashboardResponse;
import com.example.bankmanagementsystem.entity.User;
import com.example.bankmanagementsystem.repository.AccountRepository;
import com.example.bankmanagementsystem.repository.LoanRepository;
import com.example.bankmanagementsystem.repository.TransactionRepository;
import com.example.bankmanagementsystem.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final LoanRepository loanRepository;
    public AdminController(UserRepository userRepository, AccountRepository accountRepository,TransactionRepository transactionRepository,LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.accountRepository= accountRepository;
        this.transactionRepository = transactionRepository;
        this.loanRepository = loanRepository;
    }
@GetMapping("/dashboard")
public AdminDashboardResponse getDashboard() {
        return new AdminDashboardResponse(userRepository.count(),
                accountRepository.count(),
                transactionRepository.count(),
                loanRepository.count());
}
 //   @GetMapping("/users")
   // public List<User> getAllUsers() {

     //   return userRepository.findAll();
    }
