package com.example.bankmanagementsystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emi_payments")
public class EMIPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long loanId;

    private String username;

    private Double amountPaid;
    private Double emiAmount;
    private LocalDate paymentDate;
    private String status;
    public EMIPayment() {
    }

    public Long getId() {
        return id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(Double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}