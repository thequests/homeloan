package com.example.demo.services;

import java.math.BigDecimal;

import com.example.demo.entities.LoanAccount;

public interface LoanAccountService {
    LoanAccount createAccount(Long applicationId, BigDecimal principal, int tenureMonths);
}

