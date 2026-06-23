package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.entity.EMIPayment;
import com.example.bankmanagementsystem.repository.EMIPaymentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emi-payments")
@CrossOrigin("*")
public class EMIPaymentController {

    private final EMIPaymentRepository emiPaymentRepository;

    public EMIPaymentController(
            EMIPaymentRepository emiPaymentRepository) {

        this.emiPaymentRepository = emiPaymentRepository;
    }

    // User EMI History
    @GetMapping("/user/{username}")
    public List<EMIPayment> getUserPayments(
            @PathVariable String username) {

        return emiPaymentRepository.findByUsername(username);

    }

    // Admin All EMI Payments
    @GetMapping
    public List<EMIPayment> getAllPayments() {

        return emiPaymentRepository.findAll();

    }

    // Count
    @GetMapping("/count")
    public Long getCount() {

        return emiPaymentRepository.count();

    }

}