package com.bootcamp.mvp_m6.dto.product;

import java.math.BigDecimal;

public record AdminProductListDTO(
        Long id,
        BigDecimal price,
        String name,
        String categoryName,
        String brandName,
        Integer stock
) {
}
