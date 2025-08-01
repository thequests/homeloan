package com.oracle.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "emi_schedule")
public class EMISchedule {

    @Id
    @Column(name = "SCHEDULE_ID", nullable = false)
    private Long scheduleId;

    @Column(name = "ACCOUNT_NUMBER", length = 30)
    private String accountNumber;

    @Column(name = "MONTH_NUMBER")
    private Integer monthNumber;

    @Column(name = "EMI_AMOUNT")
    private Double emiAmount;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "PAID_FLAG", length = 1)
    private String paidFlag;

    @Column(name = "PAID_AMOUNT")
    private Double paidAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_ID")
    private Loan loan;

    @Column(name = "TOTAL_TENURE")
    private Integer totalTenure;

    // Constructors
    public EMISchedule() {}

    public EMISchedule(Long scheduleId, String accountNumber, Integer monthNumber,
                       Double emiAmount, LocalDate dueDate, String paidFlag) {
        this.scheduleId = scheduleId;
        this.accountNumber = accountNumber;
        this.monthNumber = monthNumber;
        this.emiAmount = emiAmount;
        this.dueDate = dueDate;
        this.paidFlag = paidFlag;
    }

    // Getters and Setters

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    public Double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(Double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPaidFlag() {
        return paidFlag;
    }

    public void setPaidFlag(String paidFlag) {
        this.paidFlag = paidFlag;
    }

    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }
    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }

    public Integer getTotalTenure() { return totalTenure; }
    public void setTotalTenure(Integer totalTenure) { this.totalTenure = totalTenure; }
}
