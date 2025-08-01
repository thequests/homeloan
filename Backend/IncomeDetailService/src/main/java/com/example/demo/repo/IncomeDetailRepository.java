package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.IncomeDetail;

public interface IncomeDetailRepository extends JpaRepository<IncomeDetail,Integer>{

}
