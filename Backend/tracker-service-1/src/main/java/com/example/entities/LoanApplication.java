package com.example.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="loanApplication")
public class LoanApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applicationId;

	private int customerId;
	private int propertyId;
	private int incomeId;
	private int loanAmount;
	private int tenure; // Assuming tenure is in months
	private Boolean applicationStatus;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime applicationDate;

	// Default Constructor
	public LoanApplication() {}
	
	// Parameterized constructor
	public LoanApplication(int applicationId, int customerId, int propertyId, int incomeId, int loanAmount, int tenure,
			Boolean applicationStatus, LocalDateTime applicationDate) {
		super();
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.propertyId = propertyId;
		this.incomeId = incomeId;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.applicationStatus = applicationStatus;
        this.applicationDate = applicationDate;
	}

	// Getters and Setters
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public int getIncomeId() {
		return incomeId;
	}
	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public Boolean getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(Boolean applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}
