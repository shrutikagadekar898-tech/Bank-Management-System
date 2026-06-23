package com.example.bankmanagementsystem.dto;

public class DashboardResponse {

    private long totalUsers;
    private long totalAccounts;
    private long totalBanks;
    private long totalLoans;
    private long totalTransactions;

    public DashboardResponse(long totalUsers,
                             long totalAccounts,
                             long totalBanks,
                             long totalLoans,
                             long totalTransactions) {

        this.totalUsers = totalUsers;
        this.totalAccounts = totalAccounts;
        this.totalBanks = totalBanks;
        this.totalLoans = totalLoans;
        this.totalTransactions = totalTransactions;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalAccounts() {
        return totalAccounts;
    }

    public long getTotalBanks() {
        return totalBanks;
    }

    public long getTotalLoans() {
        return totalLoans;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }
}