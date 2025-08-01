package com.example.service;

import java.util.List;
import java.util.Map;

public interface LoanApplicationMetricServiceInter {

	List<Integer> fetchTotalApprovedAndRejected();
    
    long fetchTotalApplicationCount();
    
    double fetchAverageApprovedLoanAmount();
    
    Map<String, Long> fetchApplicationDistributionByStatus();
    
    double fetchTotalDisbursedAmount();
    
    Map<String, Long> fetchApplicationCountByLoanType();

    double fetchAverageLoanTenure();

    long fetchApplicationCountByMonth(int year, int month);

    Map<String, Long> fetchLoanAmountDistribution();

}
