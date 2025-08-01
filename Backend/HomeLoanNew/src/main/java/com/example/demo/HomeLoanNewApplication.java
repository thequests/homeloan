package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
//@EnableFeignClients
@SpringBootApplication
public class HomeLoanNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeLoanNewApplication.class, args);
		System.out.println("Home Loan Managment System:");
	}

}
