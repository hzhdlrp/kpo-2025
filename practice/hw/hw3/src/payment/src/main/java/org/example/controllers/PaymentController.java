package org.example.controllers;

import org.example.services.PaymentService;
import org.example.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(paymentService.createAccount(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(
            @RequestParam Long userId,
            @RequestParam BigDecimal amount) {
        try {
            return ResponseEntity.ok(paymentService.deposit(userId, amount));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(paymentService.getBalance(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
