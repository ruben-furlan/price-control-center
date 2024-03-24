package com.cap.pricecontrolcenter.application.services;

import com.cap.pricecontrolcenter.domain.model.PriceModel;
import com.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;
import com.cap.pricecontrolcenter.infraestructure.exception.custom.PriceCreationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        //Given
        PriceCommand command = generateCommand();
        PriceModel priceModel = PriceModel.builder().build();
        when(this.priceRepositoryPort.save(any())).thenReturn(Optional.of(priceModel));

        // Act
        PriceModel createdPriceModel = this.priceService.create(command);

        // Assert
        assertNotNull(createdPriceModel);
    }

    @Test
    void create_UnsuccessfulSave() {
        //Given
        PriceCommand command = generateCommand();
        when(this.priceRepositoryPort.save(any())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PriceCreationException.class, () -> this.priceService.create(command));
    }

    @Test
    void findBrandAndProductToApply() {
        // Given
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

    @Test
    void findBrandAndProductToApply_is_empty() {
        // given
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;
        when(this.priceRepositoryPort.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId))
                .thenReturn(Optional.empty());
        // Act
        Optional<PriceModel> result = this.priceService.findBrandAndProductToApply(applicationDate, productId, brandId);

        // Assert
        assertTrue(result.isEmpty(), "Expected empty Optional");
    }


    private static PriceCommand generateCommand() {
        PriceCommand command = new PriceCommand(
                1,
                LocalDateTime.of(2020, 6, 14, 0, 0), // startDate
                LocalDateTime.of(2020, 12, 31, 23, 59, 59), // endDate
                1,
                35455,
                0,
                BigDecimal.valueOf(35.50), // price
                "EUR"
        );
        return command;
    }
}