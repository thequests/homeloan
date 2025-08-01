package com.example.demo.service;

import com.example.demo.entities.AdminDetails;
import com.example.demo.entities.UserAccount;
import com.example.demo.repo.AdminDetailsRepo;
import com.example.demo.repo.UserAccountRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepo userRepo;

    @Autowired
    private AdminDetailsRepo adminRepo;

    // Registration
    public String registerUser(UserAccount user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            return "Email already exists.";
        }
        if (!isValidPassword(user.getPasswordHash())) {
            return "Password does not meet security requirements.";
        }

        user.setCreatedAt(LocalDateTime.now());
        userRepo.save(user);
        return "User registered successfully.";
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        return password != null && password.matches(regex);
    }

    public List<UserAccount> getAllUsers() {
        return userRepo.findAll();
    }

    public UserAccount getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public String patchUser(Long id, Map<String, Object> updates) {
        Optional<UserAccount> optionalUser = userRepo.findById(id);
        if (optionalUser.isEmpty()) {
            return "User not found.";
        }

        UserAccount user = optionalUser.get();
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String fieldName = entry.getKey();
            Object newValue = entry.getValue();
            try {
                Field field = UserAccount.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(user, newValue);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return "Invalid field: " + fieldName;
            }
        }
        userRepo.save(user);
        return "User updated successfully.";
    }

    public String deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return "User deleted successfully!";
        } else {
            return "User not found with ID: " + id;
        }
    }

    public Map<String, Object> login(String email, String passwordHash) {
        Map<String, Object> response = new HashMap<>();

        Optional<AdminDetails> optionalAdmin = adminRepo.findByEmail(email);
        if (optionalAdmin.isPresent() && optionalAdmin.get().getPasswordHash().equals(passwordHash)) {
            response.put("status", "success");
            response.put("role", "admin");
            response.put("message", "Admin login successful.");
            return response;
        }

        Optional<UserAccount> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isPresent() && optionalUser.get().getPasswordHash().equals(passwordHash)) {
            response.put("status", "success");
            response.put("role", "user");
            response.put("message", "User login successful.");
            return response;
        }

        response.put("status", "error");
        response.put("message", "Invalid email or password.");
        return response;
    }

    public String resetPassword(String email, String newPassword) {
        Optional<UserAccount> optionalUser = userRepo.findByEmail(email);

        if (optionalUser.isPresent()) {
            UserAccount user = optionalUser.get();
            user.setPasswordHash(newPassword);
            userRepo.save(user);
            return "Password reset successful.";
        } else {
            return "User not found.";
        }
    }
    
    public String logout(String email) {
        boolean isUser = userRepo.existsByEmail(email);
        boolean isAdmin = adminRepo.existsByEmail(email);

        if (isUser || isAdmin) {
            return "Logout successful for " + email;
        } else {
            return "User/Admin not found with given email.";
        }
    }

}
