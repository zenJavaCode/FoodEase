package com.healthy.FoodEase.lambdaPractice;

import java.math.BigDecimal;

public record Transaction(
        String id,
        BigDecimal amount,
        String currency,
        TransactionType type,
        TransactionStatus status
) {
}
