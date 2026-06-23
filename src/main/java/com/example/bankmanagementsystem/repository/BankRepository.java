package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository
        extends JpaRepository<Bank, Long> {
}