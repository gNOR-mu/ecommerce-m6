package com.bootcamp.mvp_m6.controller;

import com.bootcamp.mvp_m6.dto.product.ProductFormDTO;
import com.bootcamp.mvp_m6.service.BrandService;
import com.bootcamp.mvp_m6.service.CategoryService;
import com.bootcamp.mvp_m6.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    @GetMapping
    public String productManagement(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product";
    }

    @GetMapping("/form")
    public String newProduct(
            Model model,
            @RequestParam(required = false) Long id) {

        ProductFormDTO product = id == null
                ? new ProductFormDTO()
                : productService.getProductForm(id);

        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", product);

        return "admin/productForm";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable @NotNull Long id,
            RedirectAttributes redirectAttributes) {

        productService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Producto eliminado.");
        return "redirect:/admin/products";
    }

    /**
     * Crea un nuevo producto
     *
     * @param dto DTO con información del producto a crear
     * @return
     */
    @PostMapping
    public String saveProduct(
            @Valid @ModelAttribute ProductFormDTO dto,
            RedirectAttributes redirectAttributes) {

        productService.create(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Producto %s creado.".formatted(dto.getName()));
        return "redirect:/admin/products";
    }

    /**
     * Actualiza un producto
     *
     * @param dto DTO con información del producto a crear
     * @return
     */
    @PutMapping("/{id}")
    public String updateProduct(
            @Valid @ModelAttribute ProductFormDTO dto,
            @PathVariable @NotNull Long id,
            RedirectAttributes redirectAttributes
    ) {

        productService.update(id, dto);
        redirectAttributes.addFlashAttribute("successMessage", "Producto actualizado correctamente.");
        return "redirect:/admin/products";
    }

    @GetMapping("/search")
    public String search(
            Model model,
            @RequestParam @NotNull String searchText){

        model.addAttribute("products", productService.search(searchText));
        return "admin/product";
    }
}
