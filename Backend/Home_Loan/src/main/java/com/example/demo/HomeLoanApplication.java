package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo", "com.oracle.controller", "com.oracle.services"})
@EntityScan(basePackages = {"com.oracle.entities"})
@EnableJpaRepositories(basePackages = {"com.oracle.repo"})
public class HomeLoanApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeLoanApplication.class, args);
    }
}
