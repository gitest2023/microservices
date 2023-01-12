package com.hdsaison.apigatewayservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class ApiGatewayController {
    @GetMapping
    public String index() {
        return "Hello, Api Gateway";
    }
}
