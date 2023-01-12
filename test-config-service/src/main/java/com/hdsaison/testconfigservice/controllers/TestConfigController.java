package com.hdsaison.testconfigservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope // Refresh configuration when GIT changes
@RequestMapping("/api/configs")
@RestController
public class TestConfigController {
    // Get value of `message` property in .properties file
    @Value("${message:Hello default}")
    private String message;
    @Value("${test.role:null}")
    private String role;
    @Value("${test.password:null}")
    private String password;

    @GetMapping("/message")
    public String getMessage() {
        return String.format("Message: %s - Role: %s - Password: %s", this.message, this.role, this.password);
    }
}
