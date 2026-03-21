package com.bootcamp.mvp_m6.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * DTO con información simplificada de un producto
 */
@Getter
@Setter
@Builder
public class ProductResumeDTO {
    private Long id;
    private String name;
    private String shortDescription;
    private String urlImage;
    private BigDecimal price;
}