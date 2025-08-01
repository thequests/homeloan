package com.example.demo.repo;

import com.example.demo.entities.AdminDetails;
import com.example.demo.entities.UserAccount;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDetailsRepo extends JpaRepository<AdminDetails, Long> {
	Optional<AdminDetails> findByEmail(String email);
	boolean existsByEmail(String email);
}
