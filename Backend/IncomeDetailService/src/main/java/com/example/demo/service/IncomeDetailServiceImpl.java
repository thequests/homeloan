package com.example.demo.service;

import com.example.demo.entity.IncomeDetail;
import com.example.demo.repo.IncomeDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeDetailServiceImpl implements IncomeDetailService {

    @Autowired
    private IncomeDetailRepository incomeDetailRepository;

    @Override
    public IncomeDetail saveIncomeDetail(IncomeDetail incomeDetail) {
        return incomeDetailRepository.save(incomeDetail);
    }

    @Override
    public IncomeDetail getIncomeDetailById(Integer id) {
        return incomeDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<IncomeDetail> getAllIncomeDetails() {
        return incomeDetailRepository.findAll();
    }

    @Override
    public IncomeDetail updateIncomeDetail(Integer id, IncomeDetail updatedIncomeDetail) {
        Optional<IncomeDetail> existing = incomeDetailRepository.findById(id);
        if (existing.isPresent()) {
            IncomeDetail income = existing.get();
            income.setUserId(updatedIncomeDetail.getUserId());
            income.setMonthlySalary(updatedIncomeDetail.getMonthlySalary());
            income.setOtherIncome(updatedIncomeDetail.getOtherIncome());
            income.setEmployerName(updatedIncomeDetail.getEmployerName());
            income.setEmployerType(updatedIncomeDetail.getEmployerType());
            return incomeDetailRepository.save(income);
        }
        return null;
    }

    @Override
    public void deleteIncomeDetail(Integer id) {
        incomeDetailRepository.deleteById(id);
    }
}
