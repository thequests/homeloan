package com.oracle.controller;

import com.oracle.entities.LoanAccount;
import com.oracle.services.LoanAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emi")
public class LoanAccountController {
    @Autowired
    private LoanAccountService loanAccountService;

    @PostMapping("/add")
    public ResponseEntity<LoanAccount> addLoanAccount(@RequestBody LoanAccount loanAccount) {
        return ResponseEntity.ok(loanAccountService.saveLoanAccount(loanAccount));
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<?> getByAccountNumber(@PathVariable String accountNumber) {
        Optional<LoanAccount> loanAccount = loanAccountService.getByAccountNumber(accountNumber);
        return loanAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<LoanAccount> getByApplicationId(@PathVariable Long applicationId) {
        return ResponseEntity.ok(loanAccountService.getByApplicationId(applicationId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanAccount>> getAllLoanAccounts() {
        return ResponseEntity.ok(loanAccountService.getAllLoanAccounts());
    }
} 