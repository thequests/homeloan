package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
// Explicitly tell Spring to scan all packages under "com.example" for components like @RestController and @Service
@ComponentScan(basePackages = "com.example")
// Explicitly tell Spring Data JPA where to find your repository interfaces
@EnableJpaRepositories(basePackages = "com.example.repo")
// Explicitly tell JPA (Hibernate) where to find your @Entity classes
@EntityScan(basePackages = "com.example.entities")
public class TrackerService1Application {

	public static void main(String[] args) {
		SpringApplication.run(TrackerService1Application.class, args);
	}

}
