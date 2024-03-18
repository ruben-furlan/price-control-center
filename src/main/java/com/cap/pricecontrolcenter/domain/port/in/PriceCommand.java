package com.cap.pricecontrolcenter.domain.port.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public record PriceCommand(
        @NotNull(message = "El ID de la marca no puede ser nulo")
        Integer brandId,

        @NotNull(message = "La fecha de inicio no puede ser nula")
        LocalDateTime startDate,

        @NotNull(message = "La fecha de fin no puede ser nula")
        LocalDateTime endDate,

        @NotNull(message = "El ID de la lista de precios no puede ser nulo")
        @Positive(message = "El ID de la lista de precios debe ser un número positivo")
        Integer priceList,

        @NotNull(message = "El ID del producto no puede ser nulo")
        @Positive(message = "El ID del producto debe ser un número positivo")
        Integer productId,

        @NotNull(message = "La prioridad no puede ser nula")
        @Min(value = 1, message = "La prioridad debe ser mayor o igual a 1")
        @Max(value = 10, message = "La prioridad debe ser menor o igual a 10")
        Integer priority,

        @NotNull(message = "El precio no puede ser nulo")
        @Positive(message = "El precio debe ser un número positivo")
        BigDecimal price,

        @NotNull(message = "La moneda no puede ser nula")
        String currency

) {
    public PriceCommand(
            Integer brandId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Integer priceList,
            Integer productId,
            Integer priority,
            BigDecimal price,
            String currency) {
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.productId = productId;
        this.priority = priority;
        this.price = price;
        this.currency = currency;
    }

}

