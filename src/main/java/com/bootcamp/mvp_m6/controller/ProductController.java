package com.bootcamp.mvp_m6.controller;

import com.bootcamp.mvp_m6.service.ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String products(Model model){
        model.addAttribute("products", productService.findAllResume());
        return "products";
    }

    @GetMapping("/{id}")
    public String products(Model model,
                           @NotNull @PathVariable Long id){
        model.addAttribute("product", productService.findInfoById(id));
        return "productId";
    }
}
