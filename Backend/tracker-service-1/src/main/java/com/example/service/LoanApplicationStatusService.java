package com.example.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.LoanApplication;
import com.example.repo.LoanApplicationStatusRepository;

@Service
public class LoanApplicationStatusService implements LoanApplicationStatusServiceInter{

	@Autowired
	private LoanApplicationStatusRepository loanRepo;
	
	
	public LoanApplicationStatusService(LoanApplicationStatusRepository loanRepo) {
        this.loanRepo = loanRepo;
    }
	
	
	@Override
	public List<LoanApplication> fetchAllLoanApplication(){
		return loanRepo.findAll();
	}
	
	@Override
	public Optional<LoanApplication> fetchByApplicationId(int applicationId){
		return loanRepo.findById(applicationId);
	}
	
	@Override
	public Optional<LoanApplication> fetchByCustomerId(int customerId){
		return loanRepo.findByCustomerId(customerId);
	}
	
	
	@Override
	public Boolean checkStatusofApplication(int customerId) {
		for (LoanApplication application:loanRepo.findAll()) {
			if(application.getCustomerId() == customerId) {
				return true;				
			}
		}
		return false;
	}
}
