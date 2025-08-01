package com.example.demo.controller;

import com.example.demo.entity.IncomeDetail;
import com.example.demo.service.IncomeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income-details")
public class IncomeDetailController {

    @Autowired
    private IncomeDetailService incomeDetailService;

    @PostMapping
    public IncomeDetail createIncomeDetail(@RequestBody IncomeDetail incomeDetail) {
        return incomeDetailService.saveIncomeDetail(incomeDetail);
    }

    @GetMapping("/{id}")
    public IncomeDetail getIncomeDetailById(@PathVariable Integer id) {
        return incomeDetailService.getIncomeDetailById(id);
    }

    @GetMapping
    public List<IncomeDetail> getAllIncomeDetails() {
        return incomeDetailService.getAllIncomeDetails();
    }

    @PutMapping("/{id}")
    public IncomeDetail updateIncomeDetail(@PathVariable Integer id, @RequestBody IncomeDetail incomeDetail) {
        return incomeDetailService.updateIncomeDetail(id, incomeDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteIncomeDetail(@PathVariable Integer id) {
        incomeDetailService.deleteIncomeDetail(id);
    }
}
