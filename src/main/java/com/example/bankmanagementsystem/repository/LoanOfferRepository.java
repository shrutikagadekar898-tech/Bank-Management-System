package com.example.bankmanagementsystem.repository;

import com.example.bankmanagementsystem.entity.LoanOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanOfferRepository
        extends JpaRepository<LoanOffer, Long> {

}