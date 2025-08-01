package com.oracle.controller;

import com.oracle.entities.EMISchedule;
import com.oracle.entities.Loan;
import com.oracle.entities.User;
import com.oracle.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emi/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<User> getUserByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(userService.getUserByPhone(phone));
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<?> getUserFullInfo(@PathVariable Long userId) {
        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOpt.get();
        List<Loan> loans = user.getLoans();
        List<Object> loanInfos = new java.util.ArrayList<>();
        for (Loan loan : loans) {
            List<EMISchedule> emis = loan.getEmiSchedules();
            long totalEmis = emis.size();
            long unpaidEmis = emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).count();
            double totalUnpaidAmount = emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).mapToDouble(e -> e.getEmiAmount()).sum();
            double outstandingWithInterest = loan.getOutstandingAmount() * (1 + (loan.getInterestRate() / 100.0));
            List<Object> emiDetails = new java.util.ArrayList<>();
            for (EMISchedule e : emis) {
                emiDetails.add(new java.util.HashMap<String, Object>() {{
                    put("monthNumber", e.getMonthNumber());
                    put("scheduledAmount", e.getEmiAmount());
                    put("paidAmount", e.getPaidAmount());
                    put("dueDate", e.getDueDate());
                    put("paidFlag", e.getPaidFlag());
                }});
            }
            loanInfos.add(new java.util.HashMap<String, Object>() {{
                put("loan", loan);
                put("emis", emiDetails);
                put("totalEmis", totalEmis);
                put("unpaidEmis", unpaidEmis);
                put("totalUnpaidAmount", totalUnpaidAmount);
                put("outstandingWithInterest", outstandingWithInterest);
            }});
        }
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("user", user);
            put("loans", loanInfos);
        }});
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        User saved = userService.registerUser(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody java.util.Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");
        User user = userService.authenticate(email, password);
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserId(id);
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deleted User with ID: " + id);
    }
} 