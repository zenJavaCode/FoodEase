package com.healthy.FoodEase.lambdaPractice;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class Driver {

    @Autowired
    private MockTransactionRepository repository;
    public static void main(String[] args) {


    }

    public BigDecimal calculateTotalCompletedDebits(String accountId, String currency) {


        List<Transaction> transactions = repository.findAllByAccountId(accountId);

        BigDecimal total = BigDecimal.ZERO;
        for (Transaction tx : transactions) {
            if (tx.status() == TransactionStatus.COMPLETED &&
                    tx.type() == TransactionType.DEBIT &&
                    tx.currency().equals(currency)) {
                total = total.add(tx.amount());
            }
        }
        return total;
    }


    public BigDecimal calculateTotalCompletedDebitsStreams(String accountId, String currency) {

        return repository.findAllByAccountId(accountId).stream().filter(tx -> tx.status()== TransactionStatus.COMPLETED)
                .filter(tx -> tx.type() == TransactionType.DEBIT)
                .filter( tx -> tx.currency().equals(currency))
                .map(Transaction::amount).reduce(BigDecimal.ZERO,BigDecimal::add);
    }



    public Map<TransactionStatus, List<Transaction>> groupTransactionsByStatus(String accountId) {

        Map<TransactionStatus,List<Transaction>> groupedMap = new HashMap<>();
        List<Transaction> transactions = repository.findAllByAccountId(accountId);
        for(Transaction tx  : transactions ){
            groupedMap.computeIfAbsent(tx.status(), k -> new ArrayList<>()).add(tx);

        }
        return groupedMap;

    }

    public Map<TransactionStatus, List<Transaction>> groupTransactionsByStatusUsingLambda(String accountId) {

       return  repository.findAllByAccountId(accountId).stream()
               .collect(groupingBy(Transaction::status));

    }


}