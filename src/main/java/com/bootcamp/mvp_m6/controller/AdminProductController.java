package com.bootcamp.mvp_m6.controller;

import com.bootcamp.mvp_m6.dto.product.ProductFormDTO;
import com.bootcamp.mvp_m6.service.BrandService;
import com.bootcamp.mvp_m6.service.CategoryService;
import com.bootcamp.mvp_m6.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/products")
@Slf4j
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @GetMapping
    public String productManagement(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product";
    }

    @GetMapping("/form")
    public String newProduct(
            @RequestParam(required = false) Long id,
            Model model) {

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

        try {
            productService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Producto eliminado.");

        } catch (Exception e) {
            log.error("Error al intentar eliminar un producto: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Ha ocurrido un error al intentar eliminar el producto");
        }
        return "redirect:/admin/products";
    }

    /**
     * Crea un nuevo producto
     *
     * @param dto DTO con información del producto a crear
     * @return Redirección hacia /admin/products
     */
    @PostMapping
    public String saveProduct(
            @Valid @ModelAttribute("product") ProductFormDTO dto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (bindingResult.hasErrors()) {
            //los vuelvo a agregar
            model.addAttribute("brands", brandService.findAll());
            model.addAttribute("categories", categoryService.findAll());
            return "admin/productForm";
        }

        try{
            productService.create(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Producto %s creado.".formatted(dto.getName()));
        }catch (Exception e){
            log.error("Error al intentar crear un producto: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Ha ocurrido un error al intentar crear el producto");
        }

        return "redirect:/admin/products";
    }

    /**
     * Actualiza un producto
     *
     * @param dto DTO con información del producto a crear
     * @return Redirección hacia /admin/products
     */
    @PutMapping("/{id}")
    public String updateProduct(
            @Valid @ModelAttribute("product") ProductFormDTO dto,
            BindingResult bindingResult,
            @PathVariable @NotNull Long id,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/productForm";
        }
        try{
            productService.update(id, dto);
            redirectAttributes.addFlashAttribute("successMessage", "Producto actualizado correctamente.");
        }catch (Exception e){
            log.error("Error al intentar actualizar un producto: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Ha ocurrido un error al intentar actualizar el producto");
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/search")
    public String search(
            Model model,
            @RequestParam @NotNull String searchText) {

        model.addAttribute("products", productService.search(searchText));
        return "admin/product";
    }
}
