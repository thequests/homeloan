package com.example.demo.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmiCalculator {

    public static BigDecimal calculateEmi(BigDecimal principal, int tenureMonths, double annualRate) {
        double monthlyRate = annualRate / 12 / 100;
        double emi = (principal.doubleValue() * monthlyRate * Math.pow(1 + monthlyRate, tenureMonths)) /
                     (Math.pow(1 + monthlyRate, tenureMonths) - 1);

        return BigDecimal.valueOf(emi).setScale(2, RoundingMode.HALF_UP);
    }
}
