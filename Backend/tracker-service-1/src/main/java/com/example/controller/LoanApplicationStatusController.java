package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.LoanApplication;
import com.example.service.LoanApplicationStatusService;

@RestController
@RequestMapping("/api/application/status")
public class LoanApplicationStatusController {
	@Autowired
	private LoanApplicationStatusService service;

	@GetMapping("/all")
	public List<LoanApplication> getStatusApplication() {
		return service.fetchAllLoanApplication();
	}
	@GetMapping("/{application_id}")
	public Optional<LoanApplication> getStatusByApplicationId(@PathVariable int application_id){
		return service.fetchByApplicationId(application_id);
	}
	@GetMapping("/customer/{customerId}")
    public Optional<LoanApplication> getStatusByCustomerId(@PathVariable Integer customerId) {
        return service.fetchByCustomerId(customerId);
    }
	@GetMapping("/customer/status/{customerId}")
	public Boolean getCheckStatusofUser(@PathVariable Integer customerId) {
		return service.checkStatusofApplication(customerId);
	}
	
}
