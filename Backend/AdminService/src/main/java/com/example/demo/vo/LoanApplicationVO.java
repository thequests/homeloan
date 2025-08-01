package com.example.demo.vo;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanApplicationVO {
    public LoanApplicationVO(Long applicationId, String applicantName, BigDecimal requestedAmount,
			BigDecimal monthlyIncome, String propertyDetails, String status, Integer tenureMonths,
			LocalDateTime createdDate) {
		super();
		this.applicationId = applicationId;
		this.applicantName = applicantName;
		this.requestedAmount = requestedAmount;
		this.monthlyIncome = monthlyIncome;
		this.propertyDetails = propertyDetails;
		this.status = status;
		this.tenureMonths = tenureMonths;
		this.createdDate = createdDate;
	}
	private Long applicationId;
    private String applicantName;
    private BigDecimal requestedAmount;
    private BigDecimal monthlyIncome;
    private String propertyDetails;
    private String status;
    private Integer tenureMonths;
    private LocalDateTime createdDate;
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long id) {
		this.applicationId = id;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(BigDecimal requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getPropertyDetails() {
		return propertyDetails;
	}
	public void setPropertyDetails(String propertyDetails) {
		this.propertyDetails = propertyDetails;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTenureMonths() {
		return tenureMonths;
	}
	public void setTenureMonths(Integer tenureMonths) {
		this.tenureMonths = tenureMonths;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

    
}
