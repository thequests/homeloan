package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.vo.User;
@FeignClient(name = "USER-SERVICE", url = "http://localhost:8081", path = "/user")

public interface UserClient {
	
	@GetMapping("/{id}")
    public User getUserByUserId(@PathVariable("id") int id);
}