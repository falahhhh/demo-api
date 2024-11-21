package com.domain.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/welcome")
public class WelcomeController{

    @GetMapping
    public String Welcome() {
        return "Welcome to Spring Boot Rest API";
    }
    

}