package com.oracle.controller;

import com.oracle.entities.EMISchedule;
import com.oracle.services.EMIScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.oracle.entities.Loan;

@RestController
@RequestMapping("/api/emi/emi")
public class EMIScheduleController {

    @Autowired
    private EMIScheduleService emiScheduleService;

    @GetMapping("/all")
    public ResponseEntity<List<EMISchedule>> getAllSchedules() {
        return ResponseEntity.ok(emiScheduleService.getAllSchedules());
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<EMISchedule>> getByAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(emiScheduleService.getSchedulesByAccount(accountNumber));
    }

    @GetMapping("/account/details/{accountNumber}")
    public ResponseEntity<?> getAccountEmiDetails(@PathVariable String accountNumber) {
        List<EMISchedule> emis = emiScheduleService.getSchedulesByAccount(accountNumber);
        if (emis.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Loan loan = emis.get(0).getLoan();
        long totalEmis = emis.size();
        long unpaidEmis = emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).count();
        double totalUnpaidAmount = emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).mapToDouble(EMISchedule::getEmiAmount).sum();
        double outstandingWithInterest = loan != null ? loan.getOutstandingAmount() * (1 + (loan.getInterestRate() / 100.0)) : 0.0;
        Integer totalTenure = emis.get(0).getTotalTenure(); // Get total tenure from first EMI
        List<Object> emiDetails = new java.util.ArrayList<>();
        for (EMISchedule e : emis) {
            emiDetails.add(new java.util.HashMap<String, Object>() {{
                put("monthNumber", e.getMonthNumber());
                put("scheduledAmount", e.getEmiAmount());
                put("paidAmount", e.getPaidAmount());
                put("dueDate", e.getDueDate());
                put("paidFlag", e.getPaidFlag());
                put("totalTenure", e.getTotalTenure());
            }});
        }
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("accountNumber", accountNumber);
            put("loan", loan);
            put("emis", emiDetails);
            put("totalEmis", totalEmis);
            put("unpaidEmis", unpaidEmis);
            put("totalUnpaidAmount", totalUnpaidAmount);
            put("outstandingWithInterest", outstandingWithInterest);
            put("totalTenure", totalTenure);
        }});
    }

    @GetMapping("/account/{accountNumber}/summary")
    public ResponseEntity<?> getAccountEmiSummary(@PathVariable String accountNumber) {
        List<EMISchedule> emis = emiScheduleService.getSchedulesByAccount(accountNumber);
        if (emis.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        double totalPaid = emis.stream().filter(e -> "Y".equalsIgnoreCase(e.getPaidFlag())).mapToDouble(e -> e.getPaidAmount() != null ? e.getPaidAmount() : 0.0).sum();
        double totalOutstanding = emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).mapToDouble(EMISchedule::getEmiAmount).sum();
        long emisLeft = emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).count();
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("accountNumber", accountNumber);
            put("totalPaid", totalPaid);
            put("totalOutstanding", totalOutstanding);
            put("emisLeft", emisLeft);
            put("emis", emis);
        }});
    }

    @PostMapping("/add")
    public ResponseEntity<EMISchedule> addSchedule(@RequestBody EMISchedule schedule) {
        return ResponseEntity.ok(emiScheduleService.addSchedule(schedule));
    }

    @PostMapping("/advance-month/{accountNumber}")
    public ResponseEntity<?> advanceMonth(@PathVariable String accountNumber) {
        emiScheduleService.advanceMonth(accountNumber);
        return ResponseEntity.ok("Month advanced for account: " + accountNumber);
    }

    @PostMapping("/mark-paid")
    public ResponseEntity<?> markEmiAsPaid(@RequestParam String accountNumber, 
                                          @RequestParam Integer monthNumber, 
                                          @RequestParam Double amount) {
        emiScheduleService.markEmiAsPaid(accountNumber, monthNumber, amount);
        return ResponseEntity.ok("EMI marked as paid for account: " + accountNumber + ", month: " + monthNumber);
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payEmi(@RequestParam String accountNumber, @RequestParam Integer monthNumber, @RequestParam Double amount) {
        emiScheduleService.markEmiAsPaid(accountNumber, monthNumber, amount);
        List<EMISchedule> emis = emiScheduleService.getSchedulesByAccount(accountNumber);
        EMISchedule paidEmi = emis.stream()
            .filter(e -> e.getMonthNumber().equals(monthNumber))
            .findFirst().orElse(null);
        
        if (paidEmi == null) {
            return ResponseEntity.badRequest().body("EMI not found");
        }
        
        Loan updatedLoan = paidEmi.getLoan();
        long monthsLeft = emiScheduleService.countUnpaidMonths(accountNumber);
        return ResponseEntity.ok(new java.util.HashMap<String, Object>() {{
            put("emi", paidEmi);
            put("loan", updatedLoan);
            put("monthsLeft", monthsLeft);
        }});
    }

    @PostMapping("/create-emi")
    public ResponseEntity<?> createEmiForLoanAccount(@RequestParam String accountNumber, 
                                                   @RequestParam Integer tenure, 
                                                   @RequestParam Double emiAmount) {
        emiScheduleService.createEmiForLoanAccount(accountNumber, tenure, emiAmount);
        return ResponseEntity.ok("EMI schedule created for account: " + accountNumber + 
                               " with " + tenure + " months and EMI amount: " + emiAmount);
    }
}
