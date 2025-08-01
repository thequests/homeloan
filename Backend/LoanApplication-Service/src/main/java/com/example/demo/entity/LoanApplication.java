package com.example.demo.entity;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
@Entity
@Table(name = "loan_application")
public class LoanApplication {
	
	public LoanApplication(int applicationId, int amountRequested, int tenureMonths, LocalDateTime createDate, String status,
			int userId, int propertyId, int incomeDetailId) {
		super();
		this.applicationId = applicationId;
		this.amountRequested = amountRequested;
		this.tenureMonths = tenureMonths;
		this.createDate = createDate;
		this.status = status;
		this.userId = userId;
		this.propertyId = propertyId;
		this.incomeDetailId = incomeDetailId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "APPLICATION_ID", nullable = false)
	private int applicationId;
	@Column(name = "AMOUNT_REQUESTED")
	private int amountRequested;
	@Column(name = "TENURE_MONTHS")
	private int tenureMonths;
	@Column(name = "CREATE_DATE")
	private LocalDateTime createDate;
    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }
	@Column(name = "STATUS")
	private String status;
	@Column(name = "USER_ID")
	private int userId; // FK to user_account
	@Column(name = "PROPERTY_ID")
	private int propertyId; // FK to property
	@Column(name = "INCOME_DETAIL_ID")
	private int incomeDetailId; // FK to income_detail
	
	public LoanApplication() {};
	
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public int getIncomeDetailId() {
		return incomeDetailId;
	}
	public void setIncomeDetailId(int incomeDetailId) {
		this.incomeDetailId = incomeDetailId;
	}
	public int getAmountRequested() {
		return amountRequested;
	}
	public void setAmountRequested(int amountRequested) {
		this.amountRequested = amountRequested;
	}
	public int getTenureMonths() {
		return tenureMonths;
	}
	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}