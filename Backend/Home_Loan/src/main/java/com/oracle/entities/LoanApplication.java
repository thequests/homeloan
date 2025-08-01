package com.oracle.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

enum ApplicationStatus { // Added enum for better status handling
    APPLIED, VERIFICATION, APPROVED, REJECTED
}

@Entity
@Table(name = "loan_application")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICATION_ID")
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ApplicationStatus status; // Changed to Enum for consistency

    @CreationTimestamp
    @Column(name = "APPLICATION_DATE")
    private LocalDate applicationDate; // Consolidated dates; removed redundant verification/approval dates (handle via status changes)

    // Removed redundant embedded fields (propertyLocation, propertyName, estimatedCost, monthlyIncome) - use separate entities


    @Column(name = "LOAN_AMOUNT_REQUESTED")
    private Double loanAmountRequested;

    // Added missing fields

    private Double approvedAmount;

    private Double interestRate;
    private String rejectionReason;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @Version
    private Integer version; // For concurrency

    public LoanApplication() {}

    // Getters and Setters (standardized)
    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
    public Double getLoanAmountRequested() { return loanAmountRequested; }
    public void setLoanAmountRequested(Double loanAmountRequested) { this.loanAmountRequested = loanAmountRequested; }
    public Double getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(Double approvedAmount) { this.approvedAmount = approvedAmount; }
    public Double getInterestRate() { return interestRate; }
    public void setInterestRate(Double interestRate) { this.interestRate = interestRate; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    public LocalDate getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }
}
