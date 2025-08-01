package com.example.demo.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.vo.LoanAccountVo;

@FeignClient(name = "loan-account-service")
public interface LoanAccountClient {

    @PostMapping("/api/loan-accounts/create")
    LoanAccountVo createLoanAccount(
        @RequestParam("applicationId") Long applicationId,
        @RequestParam("principal") BigDecimal principal,
        @RequestParam("tenureMonths") int tenureMonths
    );
}

