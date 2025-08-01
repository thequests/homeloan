package com.oracle.services;

import com.oracle.entities.LoanApplication;
import com.oracle.entities.User;
import com.oracle.entities.Loan;
import com.oracle.repo.LoanApplicationRepository;
import com.oracle.services.LoanService;
import com.oracle.services.EMIScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanApplicationService {
    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    @Autowired
    private LoanService loanService;
    @Autowired
    private EMIScheduleService emiScheduleService;

    public LoanApplication apply(LoanApplication application, User user) {
        application.setUser(user);
        application.setStatus();
        application.setApplicationDate(LocalDate.now());
        return loanApplicationRepository.save(application);
    }

    public List<LoanApplication> getByUser(Long userId) {
        return loanApplicationRepository.findByUser_UserId(userId);
    }

    public Optional<LoanApplication> getById(Long id) {
        return loanApplicationRepository.findById(id);
    }

    public List<LoanApplication> getAll() {
        return loanApplicationRepository.findAll();
    }

    public LoanApplication approve(Long id) {
        Optional<LoanApplication> opt = loanApplicationRepository.findById(id);
        if (opt.isPresent()) {
            LoanApplication app = opt.get();
            app.setStatus("APPROVED");
            app.setApprovalDate(LocalDate.now());
            loanApplicationRepository.save(app);
            // Check if loan already exists for this application
            if (loanService.getLoanByAccountNumber("APP" + app.getApplicationId()) == null) {
                // Create loan and EMI schedule
                Loan loan = new Loan();
                loan.setUser(app.getUser());
                loan.setAccountNumber("APP" + app.getApplicationId());
                loan.setPrincipal(app.getLoanAmountRequested());
                loan.setInterestRate(8.5); // default
                loan.setTenure(60); // default 60 months
                loan.setOutstandingAmount(app.getLoanAmountRequested());
                loanService.saveLoan(loan);
                emiScheduleService.generateSchedule(loan);
            }
            return app;
        }
        return null;
    }

    public LoanApplication reject(Long id) {
        Optional<LoanApplication> opt = loanApplicationRepository.findById(id);
        if (opt.isPresent()) {
            LoanApplication app = opt.get();
            app.setStatus("REJECTED");
            return loanApplicationRepository.save(app);
        }
        return null;
    }

    public LoanApplication verify(Long id) {
        Optional<LoanApplication> opt = loanApplicationRepository.findById(id);
        if (opt.isPresent()) {
            LoanApplication app = opt.get();
            app.setStatus("VERIFICATION");
            app.setVerificationDate(LocalDate.now());
            return loanApplicationRepository.save(app);
        }
        return null;
    }
} 