package org.example.controllers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class OrderGatewayController {

    @Autowired
    private RestTemplate restTemplate;
    private final String orderServiceUrl = "http://order-service:8080/api/orders";

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest request) {
        return restTemplate.postForEntity(orderServiceUrl, request, Object.class);
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<?> getUserOrders(@PathVariable Long userId) {
        return restTemplate.getForEntity(orderServiceUrl + "/user/" + userId, Object.class);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return restTemplate.getForEntity(orderServiceUrl + "/" + id, Object.class);
    }
}

@Getter
class CreateOrderRequest {
    private Long userId;
    private BigDecimal amount;
    private String description;
}