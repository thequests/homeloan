package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Property;

public interface PropertyRepository extends JpaRepository<Property,Integer>{

}
