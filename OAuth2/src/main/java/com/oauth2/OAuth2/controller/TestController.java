package com.oauth2.OAuth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public String testing() {
        System.out.println("test nice!");
        return "possilbe";
    }
}
