package com.example.bankmanagementsystem.controller;
import com.example.bankmanagementsystem.repository.NotificationRepository;
import com.example.bankmanagementsystem.entity.Notification;
import com.example.bankmanagementsystem.dto.DepositRequest;
import com.example.bankmanagementsystem.dto.TransferRequest;
import com.example.bankmanagementsystem.dto.WithdrawRequest;
import com.example.bankmanagementsystem.entity.Account;
import com.example.bankmanagementsystem.entity.Transaction;
import com.example.bankmanagementsystem.repository.AccountRepository;
import com.example.bankmanagementsystem.repository.TransactionRepository;
import com.example.bankmanagementsystem.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin("*")
public class AccountController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountService service;
    private final NotificationRepository notificationRepository;
    public AccountController(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository,
            AccountService service,
            NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.service = service;
    }
    @GetMapping("/user/{username}")
    public List<Account> getUserAccounts(
            @PathVariable String username){

        return accountRepository.findByUsername(username);
    }
    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {

        return service.createAccount(account);

    }
    // All Accounts
    @GetMapping
    public List<Account> getAllAccounts() {

        return accountRepository.findAll();

    }

    // Account count
    @GetMapping("/count")
    public Long getAccountCount() {

        return accountRepository.count();

    }
    @GetMapping("/balance/{username}")
    public Double getTotalBalance(
            @PathVariable String username) {

        List<Account> accounts =
                accountRepository.findByUsername(username);

        return accounts.stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }
    // Deposit
    @PostMapping("/deposit")
    public String deposit(@RequestBody DepositRequest request) {

        Account account = accountRepository
                .findByAccountNumber(request.getAccountNumber())
                .orElse(null);

        if (account == null) {
            return "Account Not Found";
        }

        if (request.getAmount() <= 0) {
            return "Invalid Amount";
        }

        account.setBalance(
                account.getBalance() + request.getAmount());

        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(account.getAccountNumber());
        transaction.setUsername(account.getUsername());
        transaction.setType("DEPOSIT");
        transaction.setAmount(request.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);
        Notification notification = new Notification();

        notification.setUsername(account.getUsername());

        notification.setMessage("Balance updated successfully");

        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
        return "Amount Deposited Successfully";
    }

    // Withdraw
    @PostMapping("/withdraw")
    public String withdraw(@RequestBody WithdrawRequest request) {

        Account account = accountRepository
                .findByAccountNumber(request.getAccountNumber())
                .orElse(null);

        if (account == null) {
            return "Account Not Found";
        }

        if (request.getAmount() <= 0) {
            return "Invalid Amount";
        }

        if (account.getBalance() < request.getAmount()) {
            return "Insufficient Balance";
        }

        account.setBalance(
                account.getBalance() - request.getAmount());

        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(account.getAccountNumber());
        transaction.setUsername(account.getUsername());
        transaction.setType("WITHDRAW");
        transaction.setAmount(request.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);
        Notification notification = new Notification();

        notification.setUsername(account.getUsername());

        notification.setMessage("Balance updated successfully");

        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
        return "Amount Withdraw Successfully";
    }

    // Transfer
    @PostMapping("/transfer")
    public String transferMoney(
            @RequestBody TransferRequest request) {

        return service.transferMoney(
                request.getSenderAccount(),
                request.getReceiverAccount(),
                request.getAmount());
    }

}