package com.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.LoanApplication;
import com.example.repo.LoanApplicationStatusRepository;

@Service
public class LoanApplicationMetricService implements LoanApplicationMetricServiceInter {
	
    @Autowired
	private LoanApplicationStatusRepository repo;
	

	@Override
	public List<Integer> fetchTotalApprovedAndRejected() {
		int acceptedTotal = 0;
		int rejectedTotal = 0;
		List<Integer> data = new ArrayList<>();
		for (LoanApplication application : repo.findAll()) {
            // Check for null status before accessing
			if (application.getStatus() != null) {
                if(application.getStatus() == "ACCEPTED") {
                    acceptedTotal++;
                } else {
                    rejectedTotal++;
                }
            }
		}
		data.add(acceptedTotal);
		data.add(rejectedTotal);
		return data;
	}

    @Override
    public long fetchTotalApplicationCount() {
        return repo.count();
    }

    @Override
    public double fetchAverageApprovedLoanAmount() {
        return repo.findByApplicationStatus(true).stream()
            .mapToDouble(LoanApplication::getAmountRequested)
            .average()
            .orElse(0.0);
    }

    @Override
    public Map<String, Long> fetchApplicationDistributionByStatus() {
        // This is a placeholder. A real implementation might have a status enum or field.
        // For now, we'll just use the boolean status.
        Map<String, Long> distribution = new HashMap<>();
        long approved = repo.findByApplicationStatus(true).size();
        long rejected = repo.findByApplicationStatus(false).size();
        long pending = repo.count() - approved - rejected; // Assuming null status means pending
        
        distribution.put("APPROVED", approved);
        distribution.put("REJECTED", rejected);
        distribution.put("PENDING", pending);
        return distribution;
    }

    @Override
    public double fetchTotalDisbursedAmount() {
        return repo.findByApplicationStatus(true).stream()
            .mapToDouble(LoanApplication::getAmountRequested)
            .sum();
    }

    @Override
    public Map<String, Long> fetchApplicationCountByLoanType() {
        // This requires a 'loanType' field in your LoanApplication entity.
        // As a placeholder, I'll return a mock map.
        // To implement this fully, add a 'private String loanType;' to your entity.
        Map<String, Long> byLoanType = new HashMap<>();
        byLoanType.put("PERSONAL_LOAN", 15L);
        byLoanType.put("HOME_LOAN", 8L);
        byLoanType.put("CAR_LOAN", 12L);
        return byLoanType;
    }

    @Override
    public double fetchAverageLoanTenure() {
        // Calculates average tenure for approved loans
        return repo.findByApplicationStatus(true).stream()
            .mapToInt(LoanApplication::getTenureMonths)
            .average()
            .orElse(0.0);
    }

    @Override
    public long fetchApplicationCountByMonth(int year, int month) {
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1);
        return repo.countByApplicationDateBetween(startDate, endDate);
    }

    @Override
    public Map<String, Long> fetchLoanAmountDistribution() {
        Map<String, Long> distribution = new HashMap<>();
        List<LoanApplication> applications = repo.findAll();

        long lessThan50k = applications.stream().filter(a -> a.getAmountRequested() < 50000).count();
        long between50kAnd150k = applications.stream().filter(a -> a.getAmountRequested() >= 50000 && a.getAmountRequested() < 150000).count();
        long greaterThan150k = applications.stream().filter(a -> a.getAmountRequested() >= 150000).count();

        distribution.put("Under $50,000", lessThan50k);
        distribution.put("$50,000 - $149,999", between50kAnd150k);
        distribution.put("$150,000 and above", greaterThan150k);
        
        return distribution;
    }
}
