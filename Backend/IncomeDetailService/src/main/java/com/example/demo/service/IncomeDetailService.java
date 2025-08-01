package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.IncomeDetail;

public interface IncomeDetailService {
    IncomeDetail saveIncomeDetail(IncomeDetail incomeDetail);
    IncomeDetail getIncomeDetailById(Integer id);
    List<IncomeDetail> getAllIncomeDetails();
    IncomeDetail updateIncomeDetail(Integer id, IncomeDetail incomeDetail);
    void deleteIncomeDetail(Integer id);
}