package com.oracle.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan_account")
public class LoanAccount {
    @Id
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @Column(name = "APPLICATION_ID")
    private Long applicationId;

    @Column(name = "DISBURSEMENT_DATE")
    private LocalDate disbursementDate;

    @Column(name = "PRINCIPAL_AMOUNT")
    private Double principalAmount;

    @Column(name = "INTEREST_RATE")
    private Double interestRate;

    @Column(name = "EMI_AMOUNT")
    private Double emiAmount;

    @Column(name = "APPLICATON_ID")
    private String applicatonId;

    // Constructors
    public LoanAccount() {}

    public LoanAccount(String accountNumber, Long applicationId, LocalDate disbursementDate, 
                      Double principalAmount, Double interestRate, Double emiAmount, String applicatonId) {
        this.accountNumber = accountNumber;
        this.applicationId = applicationId;
        this.disbursementDate = disbursementDate;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.emiAmount = emiAmount;
        this.applicatonId = applicatonId;
    }

    // Getters and Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
    public LocalDate getDisbursementDate() { return disbursementDate; }
    public void setDisbursementDate(LocalDate disbursementDate) { this.disbursementDate = disbursementDate; }
    public Double getPrincipalAmount() { return principalAmount; }
    public void setPrincipalAmount(Double principalAmount) { this.principalAmount = principalAmount; }
    public Double getInterestRate() { return interestRate; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }
    public Double getEmiAmount() { return emiAmount; }
    public void setEmiAmount(Double emiAmount) { this.emiAmount = emiAmount; }
    public String getApplicatonId() { return applicatonId; }
    public void setApplicatonId(String applicatonId) { this.applicatonId = applicatonId; }
} 