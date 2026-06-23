package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.dto.TransferRequest;
import com.example.bankmanagementsystem.entity.Account;
import com.example.bankmanagementsystem.entity.Transaction;
import com.example.bankmanagementsystem.repository.AccountRepository;
import com.example.bankmanagementsystem.repository.TransactionRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransferController(
            AccountRepository accountRepository,
            TransactionRepository transactionRepository) {

        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    @Transactional
    public String transferMoney(
            @RequestBody TransferRequest request) {

        Account sender = accountRepository
                .findByAccountNumber(request.getSenderAccount())
                .orElse(null);

        Account receiver = accountRepository
                .findByAccountNumber(request.getReceiverAccount())
                .orElse(null);

        if (sender == null || receiver == null) {
            return "Account Not Found";
        }

        if (request.getAmount() <= 0) {
            return "Invalid Amount";
        }

        if (sender.getBalance() < request.getAmount()) {
            return "Insufficient Balance";
        }

        sender.setBalance(
                sender.getBalance() - request.getAmount());

        receiver.setBalance(
                receiver.getBalance() + request.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction = new Transaction();

        transaction.setAccountNumber(
                sender.getAccountNumber());

        transaction.setType("TRANSFER");

        transaction.setAmount(
                request.getAmount());

        transaction.setTransactionDate(
                LocalDateTime.now());

        transactionRepository.save(transaction);

        return "Transfer Successful";
    }
}