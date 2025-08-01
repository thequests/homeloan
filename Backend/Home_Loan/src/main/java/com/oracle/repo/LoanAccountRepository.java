package com.oracle.repo;

import com.oracle.entities.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanAccountRepository extends JpaRepository<LoanAccount, String> {
    LoanAccount findByApplicationId(Long applicationId);
} 