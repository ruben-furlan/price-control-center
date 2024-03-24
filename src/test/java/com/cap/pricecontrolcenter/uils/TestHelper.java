package com.cap.pricecontrolcenter.uils;

import com.cap.pricecontrolcenter.domain.model.PriceModel;
import com.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.entity.PricesEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class TestHelper {
    private final static String DEFAULT_CURRENCY = "EUR";

    public static List<PriceModel> generatePriceModelWithStartAndEndDateList(LocalDateTime startDate, LocalDateTime endDate) {
        return Collections.singletonList(generatePriceModelWithStartAndEndDate(startDate,endDate));
    }

    public static List<PricesEntity> generatePriceEntitiesWithStartAndEndDateList(LocalDateTime startDate, LocalDateTime endDate) {
        return Collections.singletonList(generatePriceEntityWithStartAndEndDate(startDate,endDate));
    }

    public static PriceModel generatePriceModelWithStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        return PriceModel.builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency(DEFAULT_CURRENCY)
                .build();
    }

    public static PricesEntity generatePriceEntityWithStartAndEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        return generatePriceEntityWithStartAndEndDateAndPriority(startDate,endDate,0);
    }

    public static PricesEntity generatePriceEntityWithStartAndEndDateAndPriority(LocalDateTime startDate, LocalDateTime endDate,Integer priority) {
        return PricesEntity.builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .productId(35455)
                .priority(priority)
                .price(BigDecimal.valueOf(35.50))
                .currency(DEFAULT_CURRENCY)
                .build();
    }

    public static void assertPriceModelEqualsEntity(PricesEntity entity, PriceModel model) {
        assertEquals(entity.getBrandId(), model.getBrandId(), "BrandId mismatch");
        assertEquals(entity.getStartDate(), model.getStartDate(), "StartDate mismatch");
        assertEquals(entity.getEndDate(), model.getEndDate(), "EndDate mismatch");
        assertEquals(entity.getPriceList(), model.getPriceList(), "PriceList mismatch");
        assertEquals(entity.getProductId(), model.getProductId(), "ProductId mismatch");
        assertEquals(entity.getPriority(), model.getPriority(), "Priority mismatch");
        assertEquals(entity.getPrice(), model.getPrice(), "Price mismatch");
        assertEquals(entity.getCurrency(), model.getCurrency(), "Currency mismatch");
    }


    public static PriceCommand generateDefaultCommand() {
        return new PriceCommand(
                1,
                LocalDateTime.of(2020, 6, 14, 0, 0), // startDate
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), // endDate
                1,
                35455,
                0,
                BigDecimal.valueOf(35.50), // price
                "EUR"
        );
    }

}
