package com.oracle.repo;

import com.oracle.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Loan findByAccountNumber(String accountNumber);
    List<Loan> findByUser_UserId(Long userId);
} 