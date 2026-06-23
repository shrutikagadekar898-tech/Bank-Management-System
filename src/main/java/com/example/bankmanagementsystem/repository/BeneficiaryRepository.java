package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BeneficiaryRepository
        extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary>findByBankName(String bankName);
    List<Beneficiary> findByUsername(String username);
    Long countByUsername(String username);
}
