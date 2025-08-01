package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

//import com.example.demo.vo.User;

@Entity
@Table(name = "loan_application")
public class LoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICATION_ID")
    private Long applicationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserId userId;

    @Column(name = "STATUS")
    private String status; // APPLIED, VERIFICATION, APPROVED, REJECTED

    @Column(name = "APPLICATION_DATE")
    private LocalDate applicationDate;

    @Column(name = "LOAN_AMOUNT_REQUESTED")
    private Double loanAmountRequested;

    // Constructors, getters, setters
    public LoanApplication() {}

    // Add full constructor, getters, setters as needed
    // ...

    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
    public Double getLoanAmountRequested() { return loanAmountRequested; }
    public void setLoanAmountRequested(Double loanAmountRequested) { this.loanAmountRequested = loanAmountRequested; }
}
