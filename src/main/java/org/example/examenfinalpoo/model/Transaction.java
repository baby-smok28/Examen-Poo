package org.example.examenfinalpoo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String id;
    private Instant createdAt;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String reason;
    private String accountId;
}