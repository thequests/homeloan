package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.LoanApplicationMetricServiceInter;

@RestController
@RequestMapping("/api/application/metric")
public class LoanApplicationMetricController {

	@Autowired
	private LoanApplicationMetricServiceInter service;
	
	/**
	 * Gets the count of accepted and rejected loan applications.
	 * @return A map with keys "Accepted" and "Rejected" and their respective counts.
	 */
	@GetMapping("/status-counts")
	public Map<String,Integer> getLoanApplicationStatusCounts() {
		List<Integer> applicationCounts = service.fetchTotalApprovedAndRejected();
		Map<String,Integer> response = new HashMap<>();
		// Assuming the service returns a list where index 0 is accepted and index 1 is rejected
		response.put("Accepted", applicationCounts.get(0));
		response.put("Rejected", applicationCounts.get(1));
		return response;
	}

	/**
	 * Gets the total number of loan applications.
	 * @return A map containing the total application count.
	 */
	@GetMapping("/total-count")
	public ResponseEntity<Map<String, Long>> getTotalApplicationCount() {
		long totalCount = service.fetchTotalApplicationCount();
		Map<String, Long> response = new HashMap<>();
		response.put("totalApplications", totalCount);
		return ResponseEntity.ok(response);
	}

	/**
	 * Gets the average loan amount for all approved applications.
	 * @return A map containing the average approved loan amount.
	 */
	@GetMapping("/average-approved-amount")
	public ResponseEntity<Map<String, Double>> getAverageApprovedLoanAmount() {
		double averageAmount = service.fetchAverageApprovedLoanAmount();
		Map<String, Double> response = new HashMap<>();
		response.put("averageApprovedAmount", averageAmount);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Gets the distribution of loan applications by their status.
	 * (e.g., PENDING, APPROVED, REJECTED, IN_REVIEW)
	 * @return A map where keys are statuses and values are their counts.
	 */
	@GetMapping("/distribution-by-status")
	public ResponseEntity<Map<String, Long>> getApplicationDistributionByStatus() {
		Map<String, Long> distribution = service.fetchApplicationDistributionByStatus();
		return ResponseEntity.ok(distribution);
	}

	/**
	 * Gets the total amount of all loans that have been disbursed (approved).
	 * @return A map containing the total disbursed amount.
	 */
	@GetMapping("/total-disbursed-amount")
	public ResponseEntity<Map<String, Double>> getTotalDisbursedAmount() {
		double totalDisbursed = service.fetchTotalDisbursedAmount();
		Map<String, Double> response = new HashMap<>();
		response.put("totalDisbursedAmount", totalDisbursed);
		return ResponseEntity.ok(response);
	}

	/**
	 * Gets the number of applications for each loan type.
	 * (e.g., PERSONAL, HOME, CAR)
	 * @return A map where keys are loan types and values are their counts.
	 */
	@GetMapping("/count-by-loan-type")
	public ResponseEntity<Map<String, Long>> getApplicationCountByLoanType() {
		Map<String, Long> byLoanType = service.fetchApplicationCountByLoanType();
		return ResponseEntity.ok(byLoanType);
	}

    /**
	 * Gets the average tenure for all approved loans.
	 * @return A map containing the average tenure in months.
	 */
	@GetMapping("/average-tenure")
    public ResponseEntity<Map<String, Double>> getAverageLoanTenure() {
        double avgTenure = service.fetchAverageLoanTenure();
        Map<String, Double> response = new HashMap<>();
        response.put("averageTenureInMonths", avgTenure);
        return ResponseEntity.ok(response);
    }

    /**
     * Gets the count of applications submitted in a specific month and year.
     * @param year The year to query.
     * @param month The month to query (1-12).
     * @return A map containing the count of applications for that period.
     */
    @GetMapping("/count-by-month")
    public ResponseEntity<Map<String, Long>> getApplicationsByMonth(
            @RequestParam int year,
            @RequestParam int month) {
        long count = service.fetchApplicationCountByMonth(year, month);
        Map<String, Long> response = new HashMap<>();
        response.put("applicationsInMonth", count);
        return ResponseEntity.ok(response);
    }

    /**
     * Gets the distribution of loan amounts in predefined buckets.
     * (e.g., "<50k", "50k-100k", ">100k")
     * @return A map where keys are amount ranges and values are the counts.
     */
    @GetMapping("/distribution-by-amount")
    public ResponseEntity<Map<String, Long>> getLoanAmountDistribution() {
        Map<String, Long> distribution = service.fetchLoanAmountDistribution();
        return ResponseEntity.ok(distribution);
    }
}
