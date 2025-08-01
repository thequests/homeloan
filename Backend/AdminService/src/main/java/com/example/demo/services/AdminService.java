package com.example.demo.services;

import java.util.List;

import com.example.demo.vo.LoanApplicationVO;

public interface AdminService {
    boolean login(String email, String passwordHash);
    LoanApplicationVO getLoanDetails(Long applicationId);
    boolean approveLoan(Long applicationId);
    boolean rejectLoan(Long applicationId);
    List<LoanApplicationVO> getAllLoanApplications();
}
