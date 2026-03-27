package com.bootcamp.mvp_m6.dto.cart;

import com.bootcamp.mvp_m6.model.User;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NewOrderDTO(

        @NotNull(message = "El total no puede ser nulo")
        @DecimalMin(value = "0.0", message = "El total no puede ser negativo")
        BigDecimal total,

        @NotNull(message = "El usuario no debe ser nulo")
        User user

) {
}
