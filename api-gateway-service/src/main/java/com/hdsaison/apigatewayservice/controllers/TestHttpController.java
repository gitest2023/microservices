package com.hdsaison.apigatewayservice.controllers;

import com.hdsaison.common.services.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test-http")
@RestController
public class TestHttpController {
    @Autowired
    Http http;

    @GetMapping
    public String index() {
        return "Hello, test call services via http request on api gateway";
    }

    @GetMapping("/test")
    public Object test3() {
        System.out.println("http => " + this.http);
        var response = http.get("http://localhost:8081/products/json");
        return response;
    }
}
