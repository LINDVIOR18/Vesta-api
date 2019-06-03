package com.vesta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SwaggerControl {

    @GetMapping
    public String greeting() {
        return "Some text";
    }
}
