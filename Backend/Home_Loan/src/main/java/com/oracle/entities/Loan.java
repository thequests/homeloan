package com.oracle.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOAN_ID")
    private Long loanId;

    @Column(name = "ACCOUNT_NUMBER", length = 30, nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "PRINCIPAL")
    private Double principal;

    @Column(name = "INTEREST_RATE")
    private Double interestRate;

    @Column(name = "TENURE")
    private Integer tenure; // in months

    @Column(name = "OUTSTANDING_AMOUNT")
    private Double outstandingAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan", cascade = CascadeType.ALL)
    private List<EMISchedule> emiSchedules;

    // Constructors
    public Loan() {}

    public Loan(Long loanId, String accountNumber, Double principal, Double interestRate, Integer tenure, Double outstandingAmount) {
        this.loanId = loanId;
        this.accountNumber = accountNumber;
        this.principal = principal;
        this.interestRate = interestRate;
        this.tenure = tenure;
        this.outstandingAmount = outstandingAmount;
    }

    // Getters and Setters
    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public Double getPrincipal() { return principal; }
    public void setPrincipal(Double principal) { this.principal = principal; }
    public Double getInterestRate() { return interestRate; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }
    public Integer getTenure() { return tenure; }
    public void setTenure(Integer tenure) { this.tenure = tenure; }
    public Double getOutstandingAmount() { return outstandingAmount; }
    public void setOutstandingAmount(Double outstandingAmount) { this.outstandingAmount = outstandingAmount; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<EMISchedule> getEmiSchedules() { return emiSchedules; }
    public void setEmiSchedules(List<EMISchedule> emiSchedules) { this.emiSchedules = emiSchedules; }
} 