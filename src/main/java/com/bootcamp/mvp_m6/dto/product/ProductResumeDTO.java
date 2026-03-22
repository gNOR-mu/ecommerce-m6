package com.bootcamp.mvp_m6.dto.product;

import lombok.*;

import java.math.BigDecimal;

/**
 * DTO con información simplificada de un producto
 */
public record ProductResumeDTO(
        Long id,
        String name,
        String shortDescription,
        String urlImage,
        BigDecimal price) {
}