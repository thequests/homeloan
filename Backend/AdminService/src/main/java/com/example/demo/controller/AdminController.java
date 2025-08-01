package com.example.demo.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.AdminService;
import com.example.demo.vo.LoanApplicationVO;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @GetMapping("/applications")
    public ResponseEntity<List<LoanApplicationVO>> getAllApplications() {
        List<LoanApplicationVO> applications = adminService.getAllLoanApplications();
        if (applications.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(applications);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        if (adminService.login(email, password)) {
            return ResponseEntity.ok("✅ Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("❌ Invalid credentials");
        }
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<LoanApplicationVO> viewApplication(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getLoanDetails(id));
    }

    @PostMapping("/application/{id}/approve")
    public ResponseEntity<String> approve(@PathVariable Long id) {
        boolean approved = adminService.approveLoan(id);
        return ResponseEntity.ok(approved ? "✅ Approved" : "❌ Rejected due to ineligibility");
    }

    @PostMapping("/application/{id}/reject")
    public ResponseEntity<String> reject(@PathVariable Long id) {
        adminService.rejectLoan(id);
        return ResponseEntity.ok("❌ Rejected");
    }
}

