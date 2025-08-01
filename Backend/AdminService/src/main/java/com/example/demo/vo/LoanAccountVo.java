package com.example.demo.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanAccountVo {

    private Long accountId;
    private Long applicationId;
    private LocalDate disbursementDate;
    private BigDecimal principalAmount;
    private double interestRate;
    private BigDecimal emiAmount;

    // Getters and setters
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }

    public LocalDate getDisbursementDate() { return disbursementDate; }
    public void setDisbursementDate(LocalDate disbursementDate) { this.disbursementDate = disbursementDate; }

    public BigDecimal getPrincipalAmount() { return principalAmount; }
    public void setPrincipalAmount(BigDecimal principalAmount) { this.principalAmount = principalAmount; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    public BigDecimal getEmiAmount() { return emiAmount; }
    public void setEmiAmount(BigDecimal emiAmount) { this.emiAmount = emiAmount; }
}
