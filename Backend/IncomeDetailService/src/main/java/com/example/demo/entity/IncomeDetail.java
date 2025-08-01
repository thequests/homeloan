package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "income_detail")
public class IncomeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer incomeDetailId;

    private Integer userId;
    private Double monthlySalary;
    private Double otherIncome;
    private String employerName;
    private String employerType;

    public IncomeDetail() {}

    public IncomeDetail(Integer userId, Double monthlySalary, Double otherIncome, String employerName, String employerType) {
        this.userId = userId;
        this.monthlySalary = monthlySalary;
        this.otherIncome = otherIncome;
        this.employerName = employerName;
        this.employerType = employerType;
    }

    // Getters & Setters
    public Integer getIncomeDetailId() { return incomeDetailId; }
    public void setIncomeDetailId(Integer id) { this.incomeDetailId = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Double getMonthlySalary() { return monthlySalary; }
    public void setMonthlySalary(Double monthlySalary) { this.monthlySalary = monthlySalary; }

    public Double getOtherIncome() { return otherIncome; }
    public void setOtherIncome(Double otherIncome) { this.otherIncome = otherIncome; }

    public String getEmployerName() { return employerName; }
    public void setEmployerName(String employerName) { this.employerName = employerName; }

    public String getEmployerType() { return employerType; }
    public void setEmployerType(String employerType) { this.employerType = employerType; }
}