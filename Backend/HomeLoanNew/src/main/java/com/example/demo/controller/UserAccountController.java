package com.example.demo.controller;

import com.example.demo.entities.UserAccount;
import com.example.demo.service.UserAccountService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applicant")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserAccount user) {
        String result = userAccountService.registerUser(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        return ResponseEntity.ok(userAccountService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserAccount user = userAccountService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("User not found with ID: " + id);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        String msg = userAccountService.patchUser(id, updates);
        if (msg.contains("successfully")) {
            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.badRequest().body(msg);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String message = userAccountService.deleteUser(id);
        if (message.contains("successfully")) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.status(404).body(message);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String passwordHash = credentials.get("passwordHash");
        Map<String, Object> result = userAccountService.login(email, passwordHash);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> data) {
        String email = data.get("email");
        String newPassword = data.get("newPassword");
        String result = userAccountService.resetPassword(email, newPassword);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String email) {
        String message = userAccountService.logout(email);
        return ResponseEntity.ok(message);
    }
}
