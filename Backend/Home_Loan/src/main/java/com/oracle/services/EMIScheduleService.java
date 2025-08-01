package com.oracle.services;

import com.oracle.entities.*;
import com.oracle.repo.*;
import com.oracle.repo.EMIScheduleRepository;
import com.oracle.repo.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EMIScheduleService {

    @Autowired
    private EMIScheduleRepository emiScheduleRepository;

    @Autowired
    private LoanRepository loanRepository;

    public List<EMISchedule> getAllSchedules() {
        return emiScheduleRepository.findAll();
    }

    public List<EMISchedule> getSchedulesByAccount(String accountNumber) {
        return emiScheduleRepository.findByAccountNumber(accountNumber);
    }

    public EMISchedule addSchedule(EMISchedule schedule) {
        return emiScheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        emiScheduleRepository.deleteById(id);
    }

    public EMISchedule payEmi(String accountNumber, Integer monthNumber, Double amount) {
        List<EMISchedule> emis = emiScheduleRepository.findByAccountNumber(accountNumber);
        EMISchedule emiToPay = emis.stream()
            .filter(e -> e.getMonthNumber().equals(monthNumber))
            .findFirst().orElse(null);
        if (emiToPay == null || "Y".equalsIgnoreCase(emiToPay.getPaidFlag())) {
            return null;
        }
        emiToPay.setPaidFlag("Y");
        emiToPay.setPaidAmount(amount); // Store the paid amount
        Loan loan = emiToPay.getLoan();
        if (loan != null) {
            double newOutstanding = loan.getOutstandingAmount() - amount;
            loan.setOutstandingAmount(Math.max(newOutstanding, 0));
            loanRepository.save(loan);
        }
        emiScheduleRepository.save(emiToPay);
        return emiToPay;
    }

    public long countUnpaidMonths(String accountNumber) {
        List<EMISchedule> emis = emiScheduleRepository.findByAccountNumber(accountNumber);
        return emis.stream().filter(e -> !"Y".equalsIgnoreCase(e.getPaidFlag())).count();
    }

    public void generateSchedule(Loan loan) {
        double principal = loan.getPrincipal();
        double annualRate = loan.getInterestRate();
        int tenure = loan.getTenure();
        double monthlyRate = annualRate / 12 / 100;
        double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenure)) / (Math.pow(1 + monthlyRate, tenure) - 1);
        LocalDate start = LocalDate.now().plusMonths(1);
        for (int i = 1; i <= tenure; i++) {
            EMISchedule schedule = new EMISchedule();
            schedule.setLoan(loan);
            schedule.setAccountNumber(loan.getAccountNumber());
            schedule.setMonthNumber(i);
            schedule.setEmiAmount(emi);
            schedule.setDueDate(start.plusMonths(i - 1));
            schedule.setPaidFlag("N");
            schedule.setPaidAmount(0.0);
            schedule.setTotalTenure(tenure); // Set total tenure from loan
            emiScheduleRepository.save(schedule);
        }
    }

    public void advanceMonth(String accountNumber) {
        List<EMISchedule> emis = emiScheduleRepository.findByAccountNumber(accountNumber);
        LocalDate currentDate = LocalDate.now();
        
        for (EMISchedule emi : emis) {
            // If due date has passed and EMI is not paid, mark as overdue
            if (emi.getDueDate().isBefore(currentDate) && !"Y".equalsIgnoreCase(emi.getPaidFlag())) {
                emi.setPaidFlag("OVERDUE");
            }
            // If due date is in current month and not paid, keep as unpaid
            else if (emi.getDueDate().getMonth() == currentDate.getMonth() && 
                     emi.getDueDate().getYear() == currentDate.getYear() && 
                     !"Y".equalsIgnoreCase(emi.getPaidFlag())) {
                emi.setPaidFlag("N");
            }
            emiScheduleRepository.save(emi);
        }
    }

    public void markEmiAsPaid(String accountNumber, Integer monthNumber, Double amount) {
        List<EMISchedule> emis = emiScheduleRepository.findByAccountNumber(accountNumber);
        EMISchedule emiToPay = emis.stream()
            .filter(e -> e.getMonthNumber().equals(monthNumber))
            .findFirst().orElse(null);
        
        if (emiToPay != null && !"Y".equalsIgnoreCase(emiToPay.getPaidFlag())) {
            emiToPay.setPaidFlag("Y");
            emiToPay.setPaidAmount(amount);
            
            // Update loan outstanding amount
            Loan loan = emiToPay.getLoan();
            if (loan != null) {
                double newOutstanding = loan.getOutstandingAmount() - amount;
                loan.setOutstandingAmount(Math.max(newOutstanding, 0));
                loanRepository.save(loan);
            }
            emiScheduleRepository.save(emiToPay);
        }
    }

    public void createEmiForLoanAccount(String accountNumber, Integer tenure, Double emiAmount) {
        LocalDate startDate = LocalDate.now().plusMonths(1);
        
        for (int i = 1; i <= tenure; i++) {
            EMISchedule schedule = new EMISchedule();
            schedule.setAccountNumber(accountNumber);
            schedule.setMonthNumber(i);
            schedule.setEmiAmount(emiAmount);
            schedule.setDueDate(startDate.plusMonths(i - 1));
            schedule.setPaidFlag("N");
            schedule.setPaidAmount(0.0);
            schedule.setTotalTenure(tenure);
            emiScheduleRepository.save(schedule);
        }
    }
}
