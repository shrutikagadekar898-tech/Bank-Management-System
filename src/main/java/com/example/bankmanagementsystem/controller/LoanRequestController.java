package com.example.bankmanagementsystem.controller;
import java.util.Map;
import com.example.bankmanagementsystem.entity.Account;
import com.example.bankmanagementsystem.repository.AccountRepository;
import com.example.bankmanagementsystem.repository.NotificationRepository;
import com.example.bankmanagementsystem.controller.NotificationController;
import com.example.bankmanagementsystem.entity.Notification;
import com.example.bankmanagementsystem.entity.Loan;
import com.example.bankmanagementsystem.entity.LoanRequest;
import com.example.bankmanagementsystem.repository.LoanRepository;
import com.example.bankmanagementsystem.repository.LoanRequestRepository;
import org.springframework.web.bind.annotation.*;
import com.example.bankmanagementsystem.repository.LoanOfferRepository;
import com.example.bankmanagementsystem.entity.LoanOffer;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loan_requests")
@CrossOrigin("*")
public class LoanRequestController {
    private final LoanOfferRepository loanOfferRepository;
    private final LoanRequestRepository loanRequestRepository;
    private final LoanRepository loanRepository;
    private final NotificationRepository notificationRepository;
    private final AccountRepository accountRepository;
   private String username;
    public LoanRequestController(
            LoanRequestRepository loanRequestRepository,
            LoanRepository loanRepository, LoanOfferRepository loanOfferRepository,
            NotificationRepository notificationRepository,  AccountRepository accountRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.loanRepository = loanRepository;
        this.loanOfferRepository = loanOfferRepository;
        this.notificationRepository = notificationRepository;
        this.accountRepository = accountRepository;
    }

    // User applies for loan
    @PostMapping("/apply/{offerId}")
    public LoanRequest applyLoan(
            @PathVariable Long offerId,
            @RequestBody Map<String, String> body) {

        String username = body.get("username");

        LoanOffer offer = loanOfferRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer Not Found"));
        Account account = accountRepository.findByUsername(username)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account Not Found"));

        LoanRequest request = new LoanRequest();

        request.setUsername(username);
        request.setAccountNumber(account.getAccountNumber());
        request.setLoanType(offer.getLoanType());

        request.setAmount(offer.getMaxAmount());

        request.setInterestRate(offer.getInterestRate());

        request.setTenureMonths(offer.getTenureMonths());

        request.setStatus("PENDING");
        System.out.println(request.getAccountNumber());

        return loanRequestRepository.save(request);
    }
    // Bank dashboard - pending requests
    @GetMapping("/pending")
    public List<LoanRequest> getPendingRequests() {

        return loanRequestRepository.findByStatus("PENDING");

    }

    // Approve loan
    @PutMapping("/{id}/approve")
    public String approveLoan(@PathVariable Long id) {

        LoanRequest request = loanRequestRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Loan Request Not Found"));

        request.setStatus("APPROVED");
        loanRequestRepository.save(request);

        Loan loan = new Loan();

        loan.setUsername(request.getUsername());
        loan.setAccountNumber(request.getAccountNumber());
        loan.setLoanType(request.getLoanType());

        loan.setPrincipalAmount(request.getAmount());

        loan.setInterestRate(request.getInterestRate());

        loan.setTenureMonths(request.getTenureMonths());

        // EMI calculation
        double principal = request.getAmount();
        double rate = request.getInterestRate() / 12 / 100;
        int months = request.getTenureMonths();

        double emi = (principal * rate * Math.pow(1 + rate, months))
                / (Math.pow(1 + rate, months) - 1);

        emi = Math.round(emi * 100.0) / 100.0;

        loan.setEmi(emi);
        loan.setRemainingAmount(principal);

        loan.setStatus("APPROVED");

        loan.setStartDate(LocalDate.now());

        loan.setEndDate(
                LocalDate.now().plusMonths(months)
        );

        loanRepository.save(loan);
        Notification notification = new Notification();

        notification.setUsername(request.getUsername());

        notification.setMessage(
                "Your loan has been approved");

        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
        return "Loan Approved Successfully";
    }
    @GetMapping("/user/{username}")
    public List<LoanRequest> getUserRequests(
            @PathVariable String username) {

        return loanRequestRepository.findByUsername(username);

    }
    // Reject loan
    @PutMapping("/{id}/reject")
    public String rejectLoan(@PathVariable Long id) {

        LoanRequest request = loanRequestRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Loan Request Not Found"));

        request.setStatus("REJECTED");

        loanRequestRepository.save(request);

        return "Loan Rejected Successfully";
    }

    // All requests
    @GetMapping
    public List<LoanRequest> getAllRequests() {

        return loanRequestRepository.findAll();

    }
}