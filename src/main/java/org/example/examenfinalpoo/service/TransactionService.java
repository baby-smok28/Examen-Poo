package org.example.examenfinalpoo.service;

import lombok.AllArgsConstructor;
import org.example.examenfinalpoo.model.Transaction;
import org.example.examenfinalpoo.model.TransactionRequest;
import org.example.examenfinalpoo.model.TransactionType;
import org.example.examenfinalpoo.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(String type) {
        return transactionRepository.findAll(type);
    }

    public List<Transaction> getTransactionsByAccount(String accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction createTransaction(String transactionType, BigDecimal amount, String reason, String accountId) {
        return createTransaction(new TransactionRequest(
                accountId,
                TransactionType.valueOf(transactionType.toUpperCase()),
                amount,
                reason
        ));
    }

    public Transaction createTransaction(TransactionRequest request) {
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                Instant.now(),
                request.getTransactionType(),
                request.getAmount(),
                request.getReason(),
                request.getAccountId()
        );
        transactionRepository.save(transaction);
        return transaction;
    }
}