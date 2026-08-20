package org.example.examenfinalpoo.controller;

import lombok.AllArgsConstructor;
import org.example.examenfinalpoo.model.Transaction;
import org.example.examenfinalpoo.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    public List<Transaction> getTransactions(@RequestParam(required = false) String type) {
        return transactionService.getTransactions(type);
    }

    @GetMapping("/accounts/{id}/transactions")
    public List<Transaction> getTransactionsByAccount(@PathVariable String id) {
        return transactionService.getTransactionsByAccount(id);
    }

    @PostMapping("/transaction")
    public Transaction createTransaction(@RequestBody Map<String, String> body) {
        return transactionService.createTransaction(
                body.get("transactionType"),
                new BigDecimal(body.get("amount")),
                body.get("reason"),
                body.get("accountId")
        );
    }
}