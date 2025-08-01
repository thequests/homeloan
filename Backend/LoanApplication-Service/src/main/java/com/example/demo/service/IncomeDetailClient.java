package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.vo.User;
@FeignClient(name = "INCOMEDETAIL-SERVICE", url = "http://localhost:8083", path = "/incomedetail")

public interface IncomeDetailClient {
	
	@GetMapping("/{id}")
    public User getIncomeByIncomeId(@PathVariable("id") int id);
}