package com.example.demo.vo;



import java.math.BigDecimal;

public class IncomeDetailVo {
    private Long incomeDetailId;
    private BigDecimal monthlySalary;
    private BigDecimal otherIncome;
    private String employerName;
	public Long getIncomeDetailId() {
		return incomeDetailId;
	}
	public void setIncomeDetailId(Long incomeDetailId) {
		this.incomeDetailId = incomeDetailId;
	}
	public BigDecimal getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(BigDecimal monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	public BigDecimal getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
}

