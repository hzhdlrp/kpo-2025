package org.example.controllers;

import org.example.Services.PaymentService;
import org.example.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestParam Long userId) {
        return ResponseEntity.ok(paymentService.createAccount(userId));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(
            @RequestParam Long userId,
            @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(paymentService.deposit(userId, amount));
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam Long userId) {
        return ResponseEntity.ok(paymentService.getBalance(userId));
    }
}
