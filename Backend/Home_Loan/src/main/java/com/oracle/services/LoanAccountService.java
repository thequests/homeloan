package com.oracle.services;

import com.oracle.entities.LoanAccount;
import com.oracle.repo.LoanAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanAccountService {
    @Autowired
    private LoanAccountRepository loanAccountRepository;

    public LoanAccount saveLoanAccount(LoanAccount loanAccount) {
        return loanAccountRepository.save(loanAccount);
    }

    public Optional<LoanAccount> getByAccountNumber(String accountNumber) {
        return loanAccountRepository.findById(accountNumber);
    }

    public LoanAccount getByApplicationId(Long applicationId) {
        return loanAccountRepository.findByApplicationId(applicationId);
    }

    public List<LoanAccount> getAllLoanAccounts() {
        return loanAccountRepository.findAll();
    }
} 