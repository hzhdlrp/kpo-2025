package org.example.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Accounts", description = "")
public class AccountsController {

    @Autowired
    private RestTemplate restTemplate;
    private final String paymentServiceUrl = "http://payment-service:8081/api/payments";

    @PostMapping
    @Operation(summary = "создать аккаунт")
    public ResponseEntity<?> createAccount(@RequestParam Long userId) {
        String url = paymentServiceUrl + "/accounts?userId=" + userId;
        return restTemplate.postForEntity(url, null, Object.class);
    }

    @GetMapping("/balance")
    @Operation(summary = "узнать баланс аккаунта")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam Long userId) {
        String url = paymentServiceUrl + "/balance?userId=" + userId;
        return restTemplate.getForEntity(url, BigDecimal.class);
    }
}
