package com.bootcamp.mvp_m6.dto.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartPricing(
        BigDecimal subtotal,
        BigDecimal totalDiscounts,
        BigDecimal totalFinal,
        List<String> discountConditions
) {
}
