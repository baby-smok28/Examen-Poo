package org.example.examenfinalpoo.service;

import lombok.AllArgsConstructor;
import org.example.examenfinalpoo.model.Account;
import org.example.examenfinalpoo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findById(String id) {
        return accountRepository.findById(id);
    }

    public BigDecimal getBalance(String accountId) {
        return accountRepository.calculateBalance(accountId);
    }
}