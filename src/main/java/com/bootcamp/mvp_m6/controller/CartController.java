package com.bootcamp.mvp_m6.controller;

import com.bootcamp.mvp_m6.dto.cart.CartSummaryDTO;
import com.bootcamp.mvp_m6.model.User;
import com.bootcamp.mvp_m6.service.CartService;
import com.bootcamp.mvp_m6.service.UserService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @GetMapping
    public String cart(
            Model model,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.getByEmail(userDetails.getUsername());

        CartSummaryDTO cart = cartService.getCartSummary(user);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public String add(
            @RequestParam Long productId,
            @RequestParam @Min(value = 1) int quantity,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.getByEmail(userDetails.getUsername());
        cartService.addToCart(user, productId, quantity);
        return "redirect:/cart";
    }

    /**
     * Elimina completamente un producto del carrito
     */
    @PostMapping("/remove")
    @PreAuthorize("isAuthenticated()")
    public String remove(
            @RequestParam Long productId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        User user = userService.getByEmail(userDetails.getUsername());
        cartService.removeFromCart(user, productId);
        return "redirect:/cart";
    }

}
