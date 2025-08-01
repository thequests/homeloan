package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entities.LoanApplication;

public interface LoanApplicationStatusServiceInter {

	List<LoanApplication> fetchAllLoanApplication();

	Optional<LoanApplication> fetchByApplicationId(int applicationId);

	Optional<LoanApplication> fetchByCustomerId(int customerId);

	Boolean checkStatusofApplication(int customerId);

}