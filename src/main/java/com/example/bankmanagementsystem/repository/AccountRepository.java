package com.example.bankmanagementsystem.repository;
import java.util.List;
import com.example.bankmanagementsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AccountRepository
        extends JpaRepository<Account, Long> {
    List<Account> findByUsername(String username);

    Optional<Account>findByAccountNumber(String accountNumber);
}