package com.test.cap.pricecontrolcenter.domain.port.in;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceCommand(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        BigDecimal price,
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

