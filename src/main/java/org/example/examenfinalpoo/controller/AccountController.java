package org.example.examenfinalpoo.controller;

import lombok.AllArgsConstructor;
import org.example.examenfinalpoo.model.Account;
import org.example.examenfinalpoo.model.Transaction;
import org.example.examenfinalpoo.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id) {
        return accountService.findById(id);
    }

    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable String id) {
        return accountService.getBalance(id);
    }
}