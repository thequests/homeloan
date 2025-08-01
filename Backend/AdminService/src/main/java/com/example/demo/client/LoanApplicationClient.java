package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.vo.LoanApplicationVO;

@FeignClient(name = "loan-application-service")
public interface LoanApplicationClient {
	@GetMapping("/api/loans")
    List<LoanApplicationVO> getAllLoanApplications();

    @GetMapping("/api/loans/{id}")
    LoanApplicationVO getLoanApplication(@PathVariable Long id);

    @PutMapping("/api/loans/{id}/status")
    void updateLoanStatus(@RequestParam Long id, @RequestParam String status);
}
