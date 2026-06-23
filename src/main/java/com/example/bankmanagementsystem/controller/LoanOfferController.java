package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.entity.LoanOffer;
import com.example.bankmanagementsystem.repository.LoanOfferRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-offers")
@CrossOrigin("*")
public class LoanOfferController {

    private final LoanOfferRepository repository;

    public LoanOfferController(LoanOfferRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/{id}")
    public LoanOffer getOfferById(
            @PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Offer Not Found"));

    }
    @GetMapping
    public List<LoanOffer> getAllOffers() {
        return repository.findAll();
    }

    @PostMapping
    public LoanOffer addOffer(
            @RequestBody LoanOffer offer) {

        return repository.save(offer);
    }
}