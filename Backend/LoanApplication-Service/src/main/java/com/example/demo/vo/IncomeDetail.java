package com.example.demo.vo;

public class IncomeDetail {
	private Integer incomeDetailId;
	private Integer customerId;
	private Double monthlySalary;
	private Double otherIncome;
	private String employerName;
	private String employerType;
	// Getters & Setters
	public Integer getIncomeDetailId() {
		return incomeDetailId;
	}
	public void setIncomeDetailId(Integer incomeDetailId) {
		this.incomeDetailId = incomeDetailId;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(Double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public Double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmployerType() {
		return employerType;
	}
	public void setEmployerType(String employerType) {
		this.employerType = employerType;
	}
}
