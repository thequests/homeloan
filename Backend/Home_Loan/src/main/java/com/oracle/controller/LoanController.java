package com.oracle.controller;

import com.oracle.entities.Loan;
import com.oracle.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.oracle.entities.EMISchedule;

@RestController
@RequestMapping("/api/emi/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanService.getLoanById(id);
        return loan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<Loan> getLoanByAccountNumber(@PathVariable String accountNumber) {
        return ResponseEntity.ok(loanService.getLoanByAccountNumber(accountNumber));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoansByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUserId(userId));
    }

    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<?> getUserLoanSummary(@PathVariable Long userId) {
        List<Loan> loans = loanService.getLoansByUserId(userId);
        List<Object> loanSummaries = new java.util.ArrayList<>();
        for (Loan loan : loans) {
            List<EMISchedule> emis = loan.getEmiSchedules();
            double totalPaid = emis.stream().filter(e -> "Y".equalsIgnoreCase(e.getPaidFlag())).mapToDouble(e -> e.getPaidAmount() != null ? e.getPaidAmount() : 0.0).sum();
            double totalOutstanding = emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).mapToDouble(EMISchedule::getEmiAmount).sum();
            long emisLeft = emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).count();
            loanSummaries.add(new java.util.HashMap<String, Object>() {{
                put("loan", loan);
                put("emis", emis);
                put("totalPaid", totalPaid);
                put("totalOutstanding", totalOutstanding);
                put("emisLeft", emisLeft);
            }});
        }
        return ResponseEntity.ok(loanSummaries);
    }

    @GetMapping("/account/{accountNumber}/details")
    public ResponseEntity<?> getLoanDetailsByAccount(@PathVariable String accountNumber) {
        Loan loan = loanService.getLoanByAccountNumber(accountNumber);
        if (loan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loan);
    }

    @GetMapping("/account/{accountNumber}/paid-amount")
    public ResponseEntity<?> getPaidAmountByAccount(@PathVariable String accountNumber) {
        Loan loan = loanService.getLoanByAccountNumber(accountNumber);
        if (loan == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Calculate paid amount from EMI schedules
        List<EMISchedule> emis = loan.getEmiSchedules();
        double totalPaid = emis.stream()
            .filter(e -> "Y".equalsIgnoreCase(e.getPaidFlag()))
            .mapToDouble(e -> e.getPaidAmount() != null ? e.getPaidAmount() : 0.0)
            .sum();
        
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("accountNumber", accountNumber);
            put("totalPaid", totalPaid);
            put("outstandingAmount", loan.getOutstandingAmount());
            put("principalAmount", loan.getPrincipal());
        }});
    }

    @PostMapping("/add")
    public ResponseEntity<Loan> addLoan(@RequestBody Loan loan) {
        return ResponseEntity.ok(loanService.saveLoan(loan));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan loan) {
        loan.setLoanId(id);
        return ResponseEntity.ok(loanService.saveLoan(loan));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok("Deleted Loan with ID: " + id);
    }
} 