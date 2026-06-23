package com.example.bankmanagementsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName;
    private String ifscCode;
    private String branchName;
    private String bankType;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
// Getters and Setters
}