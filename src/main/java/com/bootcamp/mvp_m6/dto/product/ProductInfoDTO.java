package com.bootcamp.mvp_m6.dto.product;


import java.math.BigDecimal;
import java.util.Map;

/**
 * DTO con la información de un producto
 */
public record ProductInfoDTO(
        Long id,
        BigDecimal price,
        Map<String, Object> features,
        String name,
        String urlImage,
        String description,
        String shortDescription
){}
