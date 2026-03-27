package com.bootcamp.mvp_m6.controller;

import com.bootcamp.mvp_m6.model.User;
import com.bootcamp.mvp_m6.service.CheckoutService;
import com.bootcamp.mvp_m6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private UserService userService;

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String checkout(){
        return "checkout";
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public String checkout(@AuthenticationPrincipal UserDetails userDetails){
        User user = userService.getByEmail(userDetails.getUsername());
        checkoutService.checkout(user);
        return "redirect:/checkout";
    }


}
