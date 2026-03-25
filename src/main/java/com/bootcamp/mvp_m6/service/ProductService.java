package com.bootcamp.mvp_m6.service;

import com.bootcamp.mvp_m6.dto.product.AdminProductListDTO;
import com.bootcamp.mvp_m6.dto.product.ProductFormDTO;
import com.bootcamp.mvp_m6.dto.product.ProductInfoDTO;
import com.bootcamp.mvp_m6.dto.product.ProductResumeDTO;
import com.bootcamp.mvp_m6.mapper.ProductMapper;
import com.bootcamp.mvp_m6.model.Product;
import com.bootcamp.mvp_m6.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductMapper productMapper;

    public Product create(ProductFormDTO dto) {
        dto.buildFeaturesMap();
        Product product = productMapper.toEntity(dto);
        return productRepository.save(product);
    }

    /**
     * Obtiene los productos más vendidos
     *
     * @return Una lista con los productos más vendidos
     */
    public List<ProductResumeDTO> getTopProducts() {
        return productRepository.getTopProducts();
    }

    /**
     * Obtiene un resumen de todos los productos
     *
     * @return Una lista con el resumen de todos los productos
     */
    public List<ProductResumeDTO> findAllResume() {
        return productRepository.findAllResume();
    }

    /**
     * Busca la información de un producto por ID
     *
     * @param id ID del producto
     * @return Información del producto
     */
    public ProductInfoDTO findInfoById(Long id) {
        return productRepository.findInfoById(id);
    }

    /**
     * Busca todos los productos como administrador
     *
     * @return Una lista de todos los productos
     */
    public List<AdminProductListDTO> findAll() {
        return productRepository.findAllAdmin();
    }

    /**
     * Elimina un producto
     *
     * @param id ID del producto
     */
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    /**
     * Busca un producto por ID
     *
     * @param id ID del producto
     * @return Producto con la id coincidente
     */
    public ProductFormDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        return productMapper.toDTO(product);
    }

    /**
     * Edita un producto
     *
     * @param dto Producto a editar
     */
    public void update(Long id, ProductFormDTO dto) {
        Product existing = productRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        dto.buildFeaturesMap();

        productMapper.updateEntityFromDTO(dto, existing);

        existing.setCategory(categoryService.getReferenceById(dto.getCategoryId()));
        existing.setBrand(brandService.getReferenceById(dto.getBrandId()));

        productRepository.save(existing);
    }

    /**
     * Busca productos
     *
     * @param searchText Texto a buscar
     * @return Listado con los productos coincidentes
     */
    public List<AdminProductListDTO> search(String searchText) {
        return productRepository.search(searchText);
    }

    /*


     *//**
     * Valida los campos de un producto
     * @param product Producto a validar
     *//*
    private void validateFields(ProductFormDTO product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser vacío ni estar en blanco");
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        if (product.getCategoryId() == null || !categoryDAO.existsById(product.getCategoryId())) {
            throw new IllegalArgumentException("La ID de categoría no existe");
        }

        if (product.getBrandId() == null || !brandDAO.existsById(product.getBrandId())) {
            throw new IllegalArgumentException("La ID de marca no existe");
        }
    }*/
}
