package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.vo.User;
@FeignClient(name = "PROPERTY-SERVICE", url = "http://localhost:8082", path = "/property")

public interface PropertyClient {
	
	@GetMapping("/{id}")
    public User getPropertyByPropertyId(@PathVariable("id") int id);
}