package com.bootcamp.mvp_m6.controller;

import com.bootcamp.mvp_m6.dto.user.UserPublicRegisterDTO;
import com.bootcamp.mvp_m6.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new UserPublicRegisterDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @Valid @ModelAttribute("user") UserPublicRegisterDTO dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "signup";
        }

        userService.createPublicUser(dto);
        return "redirect:/login";
    }
}