package com.example.bankmanagementsystem.controller;
import com.example.bankmanagementsystem.entity.Transaction;
import com.example.bankmanagementsystem.repository.TransactionRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @GetMapping("/statement/{username}")
    public ResponseEntity<byte[]> downloadStatement(
            @PathVariable String username) {

        try {

            List<Transaction> transactions =
                    transactionRepository.findByUsername(username);

            Document document = new Document();

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);

            document.open();

            Font titleFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            18);

            Paragraph title =
                    new Paragraph(
                            "Bank Transaction Statement",
                            titleFont);

            title.setAlignment(Element.ALIGN_CENTER);

            document.add(title);

            document.add(new Paragraph("User : " + username));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);

            table.addCell("Type");

            table.addCell("Amount");

            table.addCell("Account Number");

            table.addCell("Date");

            for (Transaction t : transactions) {

                table.addCell(t.getType());

                table.addCell(
                        String.valueOf(t.getAmount()));

                table.addCell(
                        t.getAccountNumber());

                table.addCell(
                        String.valueOf(
                                t.getTransactionDate()));

            }

            document.add(table);

            document.close();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=statement.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(out.toByteArray());

        }
        catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError()
                    .build();

        }
    }
    // All Transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Transactions by Account Number
    @GetMapping("/account/{accountNumber}")
    public List<Transaction> getTransactionsByAccount(
            @PathVariable String accountNumber) {

        return transactionRepository.findByAccountNumber(accountNumber);
    }

    // Transactions by Username
    @GetMapping("/user/{username}")

    public List<Transaction> getTransactionsByUser(
            @PathVariable String username) {

        return transactionRepository.findByUsername(username);
    }

    // Delete Transaction
    @DeleteMapping("/{id}")
    public String deleteTransaction(@PathVariable Long id) {

        transactionRepository.deleteById(id);

        return "Transaction Deleted Successfully";
    }
    @GetMapping("/count/{username}")
    public Long getTransactionCount(
            @PathVariable String username) {

        return transactionRepository.countByUsername(username);
    }
    // Transaction Count
    @GetMapping("/count")
    public Long getTransactionCount() {

        return transactionRepository.count();
    }
}