package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cinema")
public class BasePageController {

    @GetMapping
    public String basePage() {
        return "all/all";
    }
}