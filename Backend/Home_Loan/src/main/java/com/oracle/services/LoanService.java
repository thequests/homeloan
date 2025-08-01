package com.oracle.services;

import com.oracle.entities.Loan;
import com.oracle.repo.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan getLoanByAccountNumber(String accountNumber) {
        return loanRepository.findByAccountNumber(accountNumber);
    }

    public List<Loan> getLoansByUserId(Long userId) {
        return loanRepository.findByUser_UserId(userId);
    }

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
} 