package com.oracle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emi/calculator")
public class CalculatorController {
    // Eligibility: Loan amount = 60 * (0.6 * net monthly salary)
    @GetMapping("/eligibility")
    public ResponseEntity<?> eligibility(@RequestParam double monthlyIncome) {
        double eligibleAmount = 60 * (0.6 * monthlyIncome);
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("eligibleAmount", eligibleAmount);
        }});
    }

    // EMI: EMI = P*R*{((1+R)^n)/((1+R)^n-1)}
    @GetMapping("/")
    public ResponseEntity<?> emi(@RequestParam double amount, @RequestParam int tenure) {
        double annualRate = 8.5; // as per case study
        double monthlyRate = annualRate / 12 / 100;
        int n = tenure; // in months
        double emi = (amount * monthlyRate * Math.pow(1 + monthlyRate, n)) / (Math.pow(1 + monthlyRate, n) - 1);
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("emi", emi);
        }});
    }
} 