package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.LoanAccount;

public interface LoanAccountRepository extends JpaRepository<LoanAccount, String> {
}

