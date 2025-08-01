package com.example.demo.service;

import com.example.demo.entity.LoanApplication;
import java.util.List;

public interface LoanApplicationService {
    LoanApplication saveLoanApplication(LoanApplication loanApplication);
    LoanApplication getLoanApplicationById(int id);
    List<LoanApplication> getAllLoanApplications();
    LoanApplication updateLoanApplication(LoanApplication loanApplication);
    void deleteLoanApplication(int id);
}
