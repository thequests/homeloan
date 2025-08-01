package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.vo.PropertyDetailVo;

@FeignClient(name = "property-service")
public interface PropertyClient {
    @GetMapping("/api/property/{id}")
    PropertyDetailVo getPropertyDetail(@PathVariable Long id);
}

