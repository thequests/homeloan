package com.example.demo.vo;

import com.example.demo.entity.LoanApplication;

public class CinemaTemplate {
	private LoanApplication loanapplication;
	private User user;
	private Property property;
	private IncomeDetail incomedetai;
	public LoanApplication getLoanapplication() {
		return loanapplication;
	}
	public void setLoanapplication(LoanApplication loanapplication) {
		this.loanapplication = loanapplication;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public IncomeDetail getIncomedetai() {
		return incomedetai;
	}
	public void setIncomedetai(IncomeDetail incomedetai) {
		this.incomedetai = incomedetai;
	}
	
	
}