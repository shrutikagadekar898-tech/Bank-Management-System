package com.example.bankmanagementsystem.dto;

public class AdminDashboardResponse {
    private long totalUsers;
    private long totalAccounts;
    private long totalTransactions;
    private long totalLoans;
    public AdminDashboardResponse(long totalUsers, long totalAccounts,long totalTransactions, long totalLoans){
        this.totalUsers= totalUsers;
        this.totalAccounts=totalAccounts;
        this.totalTransactions=totalTransactions;
        this.totalLoans = totalLoans;
    }
    public long getTotalUsers(){
        return totalUsers;
    }
    public long getTotalAccounts(){ return totalAccounts;}
    public long getTotalTransactions(){ return totalTransactions;}
    public long getTotalLoans() { return totalLoans;}
}
