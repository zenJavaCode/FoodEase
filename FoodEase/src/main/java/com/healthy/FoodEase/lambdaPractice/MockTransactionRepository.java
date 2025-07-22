package com.healthy.FoodEase.lambdaPractice;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MockTransactionRepository {
    public List<Transaction> findAllByAccountId(String accountId) {
        // Return a list of sample transactions
        return List.of(
                new Transaction("txn_1", new BigDecimal("100.00"), "USD", TransactionType.DEBIT, TransactionStatus.COMPLETED),
                new Transaction("txn_2", new BigDecimal("250.50"), "USD", TransactionType.CREDIT, TransactionStatus.COMPLETED),
                new Transaction("txn_3", new BigDecimal("50.00"), "EUR", TransactionType.DEBIT, TransactionStatus.COMPLETED),
                new Transaction("txn_4", new BigDecimal("120.00"), "USD", TransactionType.DEBIT, TransactionStatus.PENDING),
                new Transaction("txn_5", new BigDecimal("300.00"), "USD", TransactionType.DEBIT, TransactionStatus.FAILED),
                new Transaction("txn_6", new BigDecimal("75.25"), "USD", TransactionType.DEBIT, TransactionStatus.COMPLETED)
        );
    }
}
