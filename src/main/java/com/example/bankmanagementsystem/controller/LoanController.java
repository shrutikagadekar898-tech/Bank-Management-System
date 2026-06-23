package com.example.bankmanagementsystem.controller;
import com.example.bankmanagementsystem.repository.NotificationRepository;
import com.example.bankmanagementsystem.entity.Notification;
import com.example.bankmanagementsystem.entity.Loan;
import com.example.bankmanagementsystem.repository.LoanRepository;
import org.springframework.web.bind.annotation.*;
import com.example.bankmanagementsystem.entity.EMIPayment;
import com.example.bankmanagementsystem.repository.EMIPaymentRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin("*")
public class LoanController {
    private final EMIPaymentRepository emiPaymentRepository;
    private final LoanRepository loanRepository;
private final NotificationRepository notificationRepository;
    public LoanController(LoanRepository loanRepository,EMIPaymentRepository emiPaymentRepository, NotificationRepository
                          notificationRepository) {
        this.loanRepository = loanRepository;
        this.emiPaymentRepository = emiPaymentRepository;
        this.notificationRepository= notificationRepository;
    }

    // Admin Dashboard -> all loans
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // User Dashboard -> loans of logged in user//
    @GetMapping("/user/{username}")
    public List<Loan> getLoansByUsername(
            @PathVariable String username) {

        return loanRepository.findByUsername(username);
    }

    // Bank Dashboard -> approved loans
    @GetMapping("/status/{status}")
    public List<Loan> getLoansByStatus(
            @PathVariable String status) {

        return loanRepository.findByStatus(status);
    }

    // Account based loans
    @GetMapping("/account/{accountNumber}")
    public List<Loan> getLoansByAccountNumber(
            @PathVariable String accountNumber) {

        return loanRepository.findByAccountNumber(accountNumber);
    }

    // User accepts loan offer
    @PutMapping("/{id}/accept")
    public Loan acceptLoan(@PathVariable Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan Not Found"));

        loan.setStatus("ACTIVE");

        return loanRepository.save(loan);
    }

    // User rejects offer
    @PutMapping("/{id}/reject")
    public Loan rejectLoan(@PathVariable Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan Not Found"));

        loan.setStatus("REJECTED");

        return loanRepository.save(loan);
    }
    @GetMapping("/offers/{username}")
    public List<Loan> getLoanOffers(
            @PathVariable String username) {

        return loanRepository.findByUsername(username)
                .stream()
                .filter(loan ->
                        loan.getStatus().equals("APPROVED"))
                .toList();
    }
    // Loan completed
    @PutMapping("/{id}/close")
    public Loan closeLoan(@PathVariable Long id) {

        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan Not Found"));

        loan.setStatus("CLOSED");

        return loanRepository.save(loan);
    }
    @PostMapping("/pay-emi/{loanId}")
    public String payEmi(
            @PathVariable Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() ->
                        new RuntimeException("Loan Not Found"));

        if (loan.getStatus().equals("CLOSED")) {

            return "Loan Already Closed";
        }

        EMIPayment payment = new EMIPayment();

        payment.setLoanId(loanId);

        payment.setUsername(loan.getUsername());

        payment.setAmountPaid(loan.getEmi());
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(LocalDate.now());

        emiPaymentRepository.save(payment);

        double remaining =
                loan.getRemainingAmount() - loan.getEmi();

        if (remaining <= 0) {

            remaining = 0;

            loan.setStatus("CLOSED");
        }

        loan.setRemainingAmount(remaining);

        loanRepository.save(loan);
        Notification notification = new Notification();

        notification.setUsername(loan.getUsername());

        notification.setMessage(
                "EMI paid successfully");

        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
        return "EMI Paid Successfully";
    }
    @GetMapping("/count/{username}")
    public Long getLoanCount(
            @PathVariable String username) {

        return loanRepository.countByUsername(username);
    }
    // Dashboard count
    @GetMapping("/count")
    public Long getLoanCount() {
        return loanRepository.count();
    }
}