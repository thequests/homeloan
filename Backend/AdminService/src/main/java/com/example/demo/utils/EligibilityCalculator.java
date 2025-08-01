package com.example.demo.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EligibilityCalculator {
    public static BigDecimal calculateEligibility(BigDecimal monthlyIncome) {
        return monthlyIncome.multiply(BigDecimal.valueOf(0.6))
                            .multiply(BigDecimal.valueOf(60))
                            .setScale(2, RoundingMode.HALF_UP);
    }
}

