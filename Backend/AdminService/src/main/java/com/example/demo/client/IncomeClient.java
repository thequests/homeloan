package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.vo.IncomeDetailVo;


@FeignClient(name = "income-service")
public interface IncomeClient {
    @GetMapping("/api/income/{id}")
    IncomeDetailVo getIncomeDetail(@PathVariable Long id);
}

