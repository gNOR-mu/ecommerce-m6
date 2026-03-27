package com.bootcamp.mvp_m6.dto.cart;

import java.math.BigDecimal;
import java.util.List;

public record CartSummaryDTO(
        List<CartItemDTO> items,
        CartPricing cartPricing
) {
}
