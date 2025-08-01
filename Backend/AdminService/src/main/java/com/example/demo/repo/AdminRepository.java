package com.example.demo.repo;

import com.example.demo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmailAndPasswordHash(String email, String passwordHash);
}
