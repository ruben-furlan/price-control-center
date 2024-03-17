package com.test.cap.pricecontrolcenter.application.services;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.test.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private PriceService priceService;

    @Test
    void create_SuccessfullyCreated() {
        // Arrange
        PriceCommand command = new PriceCommand(
                1, // brandId
                LocalDateTime.of(2020, 6, 14, 0, 0), // startDate
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), // endDate
                1, // priceList
                35455, // productId
                0, // priority
                BigDecimal.valueOf(35.50), // price
                "EUR" // currency
        );
        PriceModel priceModel = PriceModel.builder().build();
        when(priceRepositoryPort.save(any())).thenReturn(Optional.of(priceModel));

        // Act
        PriceModel createdPriceModel = priceService.create(command);

        // Assert
        assertNotNull(createdPriceModel);
        // Add more assertions as needed
    }

    @Test
    void findBrandAndProductToApply() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;
        List<PriceModel> expectedPrices = Collections.singletonList(PriceModel.builder().build());
        when(this.priceRepositoryPort.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId))
                .thenReturn(Optional.of(expectedPrices));

        // Act
        Optional<PriceModel> result = this.priceService.findBrandAndProductToApply(applicationDate, productId, brandId);

        // Assert
        assertEquals(Optional.of(expectedPrices.get(0)), result);
    }
}