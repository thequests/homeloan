package com.example.demo.services;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.LoanAccount;
import com.example.demo.repo.LoanAccountRepository;
import com.example.demo.utils.EmiCalculator;

@Service
public class LoanAccountServiceImpl implements LoanAccountService {

    @Autowired
    private LoanAccountRepository repository;

    @Override
    public LoanAccount createAccount(Long applicationId, BigDecimal principal, int tenureMonths) {
        BigDecimal interestRate = BigDecimal.valueOf(8.5);
        BigDecimal emi = EmiCalculator.calculateEmi(principal, tenureMonths, interestRate.doubleValue());

        LoanAccount account = new LoanAccount();
        account.setAccountNumber("ACC" + System.currentTimeMillis());
        account.setApplicationId(applicationId);
        account.setPrincipalAmount(principal);
        account.setInterestRate(interestRate);
        account.setDisbursementDate(LocalDate.now());
        account.setEmiAmount(emi);

        return repository.save(account);
    }
}

