package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ADMIN_DETAILS", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
public class AdminDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_details_seq_gen")
    @SequenceGenerator(name = "admin_details_seq_gen", sequenceName = "ADMIN_DETAILS_SEQ", allocationSize = 1)
    @Column(name = "ADMIN_ID")
    private Long adminId;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String passwordHash;

    // Added missing fields
    private String fullName;

    @UpdateTimestamp
    private LocalDateTime lastLoginDate; // Renamed/added for tracking

    public AdminDetails() {}

    public AdminDetails(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Getters and Setters
    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDateTime getLastLoginDate() { return lastLoginDate; }
    public void setLastLoginDate(LocalDateTime lastLoginDate) { this.lastLoginDate = lastLoginDate; }
}
