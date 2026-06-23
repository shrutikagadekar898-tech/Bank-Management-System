package com.example.bankmanagementsystem.controller;

import com.example.bankmanagementsystem.entity.Beneficiary;
import com.example.bankmanagementsystem.repository.BeneficiaryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiaries")
@CrossOrigin("*")
public class BeneficiaryController {

    private final BeneficiaryRepository beneficiaryRepository;

    public BeneficiaryController(BeneficiaryRepository beneficiaryRepository) {
        this.beneficiaryRepository = beneficiaryRepository;
    }
    @GetMapping("/user/{username}")
    public List<Beneficiary> getUserBeneficiaries(
            @PathVariable String username) {

        return beneficiaryRepository.findByUsername(username);
    }
    @GetMapping
    public List<Beneficiary> getAllBeneficiaries() {
        return beneficiaryRepository.findAll();
    }

    @PostMapping
    public Beneficiary addBeneficiary(
            @RequestBody Beneficiary beneficiary) {

        return beneficiaryRepository.save(beneficiary);
    }

    @DeleteMapping("/{id}")
    public String deleteBeneficiary(
            @PathVariable Long id) {

        beneficiaryRepository.deleteById(id);

        return "Beneficiary Deleted Successfully";
    }
    @GetMapping("/count/{username}")
    public Long getBeneficiaryCount(
            @PathVariable String username) {

        return beneficiaryRepository.countByUsername(username);
    }
    @GetMapping("/count")
    public Long getBeneficiaryCount() {

        return beneficiaryRepository.count();
    }
}