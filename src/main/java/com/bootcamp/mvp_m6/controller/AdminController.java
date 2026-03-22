package com.bootcamp.mvp_m6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String panel(){
        return "admin/panel";
    }

    @GetMapping("/products")
    public String productManagement(){
        return "admin/product";
    }
}
