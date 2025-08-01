package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoanApplication;
import com.example.demo.service.LoanApplicationService;

@RestController
@RequestMapping("/api/loans")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping
    public LoanApplication create(@RequestBody LoanApplication loanApplication) {
        return loanApplicationService.saveLoanApplication(loanApplication);
    }

    @GetMapping("/{id}")
    public LoanApplication getById(@PathVariable int id) {
        return loanApplicationService.getLoanApplicationById(id);
    }

    @GetMapping
    public List<LoanApplication> getAll() {
        return loanApplicationService.getAllLoanApplications();
    }

    @PutMapping("/{id}")
    public LoanApplication update(@PathVariable long id, @RequestBody LoanApplication loanApplication) {
        loanApplication.setApplicationId(id);
        return loanApplicationService.updateLoanApplication(loanApplication);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        loanApplicationService.deleteLoanApplication(id);
    }
}