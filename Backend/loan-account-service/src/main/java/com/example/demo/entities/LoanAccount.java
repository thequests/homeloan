package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loan_account")
public class LoanAccount {
	public LoanAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanAccount(String accountNumber, Long applicationId, LocalDate disbursementDate, BigDecimal principalAmount,
			BigDecimal interestRate, BigDecimal emiAmount) {
		super();
		this.accountNumber = accountNumber;
		this.applicationId = applicationId;
		this.disbursementDate = disbursementDate;
		this.principalAmount = principalAmount;
		this.interestRate = interestRate;
		this.emiAmount = emiAmount;
	}

	@Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "disbursement_date")
    private LocalDate disbursementDate;

    @Column(name = "principal_amount")
    private BigDecimal principalAmount;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "emi_amount")
    private BigDecimal emiAmount;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public LocalDate getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(LocalDate disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}
}
