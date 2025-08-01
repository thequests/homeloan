package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.LoanAccount;
import com.example.demo.services.LoanAccountService;

@RestController
@RequestMapping("/api/loan-accounts")
public class LoanAccountController {

    @Autowired
    private LoanAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<LoanAccount> createLoanAccount(@RequestParam Long applicationId,
                                                          @RequestParam BigDecimal principal,
                                                          @RequestParam int tenureMonths) {
        LoanAccount created = accountService.createAccount(applicationId, principal, tenureMonths);
        return ResponseEntity.ok(created);
    }
}

