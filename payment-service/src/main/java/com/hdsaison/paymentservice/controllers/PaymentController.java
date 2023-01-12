package com.hdsaison.paymentservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/payments")
@RestController
public class PaymentController {
    @GetMapping
    public String index() {
        return "Hello, payment";
    }
}
