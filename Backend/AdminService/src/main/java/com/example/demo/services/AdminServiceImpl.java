package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.IncomeClient;
import com.example.demo.client.LoanAccountClient;
import com.example.demo.client.LoanApplicationClient;
import com.example.demo.client.PropertyClient;
import com.example.demo.repo.AdminRepository;
import com.example.demo.utils.EligibilityCalculator;
import com.example.demo.vo.IncomeDetailVo;
import com.example.demo.vo.LoanApplicationVO;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private LoanApplicationClient loanClient;

    @Autowired
    private IncomeClient incomeClient;

    @Autowired
    private PropertyClient propertyClient;

    @Autowired
    private LoanAccountClient loanAccountClient;

    @Override
    public boolean login(String email, String passwordHash) {
        return adminRepo.findByEmailAndPasswordHash(email, passwordHash).isPresent();
    }

    @Override
    public LoanApplicationVO getLoanDetails(Long id) {
        return loanClient.getLoanApplication(id);
    }

    @Override
    public boolean approveLoan(Long id) {
        LoanApplicationVO loan = loanClient.getLoanApplication(id);
        BigDecimal eligibleAmount = EligibilityCalculator.calculateEligibility(loan.getMonthlyIncome());

        if (loan.getRequestedAmount().compareTo(eligibleAmount) <= 0) {
            loanClient.updateLoanStatus(id, "APPROVED");

           
            loanAccountClient.createLoanAccount(
                id,
                loan.getRequestedAmount(),
                loan.getTenureMonths()
            );

            return true;
        } else {
            loanClient.updateLoanStatus(id, "REJECTED");
            return false;
        }
    }

    @Override
    public boolean rejectLoan(Long id) {
        loanClient.updateLoanStatus(id, "REJECTED");
        return true;
    }

    @Override
    public List<LoanApplicationVO> getAllLoanApplications() {
        return loanClient.getAllLoanApplications();
    }
}
