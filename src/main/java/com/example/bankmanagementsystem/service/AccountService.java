package com.example.bankmanagementsystem.service;

import com.example.bankmanagementsystem.entity.Account;
import com.example.bankmanagementsystem.entity.Transaction;
import com.example.bankmanagementsystem.repository.AccountRepository;
import com.example.bankmanagementsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(Account account) {

        String accountNumber =
                String.valueOf(1000000000L +
                        new Random().nextInt(900000000));
        account.setAccountNumber(accountNumber);

        if (account.getBalance() == null) {
            account.setBalance(0.0);
        }

        return repository.save(account);
    }

    public String transferMoney(String senderAcc,
                                String receiverAcc,
                                Double amount) {

        Account sender = repository
                .findByAccountNumber(senderAcc)
                .orElseThrow(() ->
                        new RuntimeException("Sender account not found"));

        Account receiver = repository
                .findByAccountNumber(receiverAcc)
                .orElseThrow(() ->
                        new RuntimeException("Receiver account not found"));

        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        repository.save(sender);
        repository.save(receiver);

        // Sender Transaction
        Transaction senderTransaction = new Transaction();
        senderTransaction.setAccountNumber(sender.getAccountNumber());
        senderTransaction.setUsername(sender.getUsername());
        senderTransaction.setType("TRANSFER SENT");
        senderTransaction.setAmount(amount);
        senderTransaction.setTransactionDate(LocalDateTime.now());

        // Receiver Transaction
        Transaction receiverTransaction = new Transaction();
        receiverTransaction.setAccountNumber(receiver.getAccountNumber());
        receiverTransaction.setUsername(receiver.getUsername());
        receiverTransaction.setType("TRANSFER RECEIVED");
        receiverTransaction.setAmount(amount);
        receiverTransaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(senderTransaction);
        transactionRepository.save(receiverTransaction);

        return "Transfer Successful";
    }
}