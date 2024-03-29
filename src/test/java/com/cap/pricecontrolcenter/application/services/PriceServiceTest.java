package com.cap.pricecontrolcenter.application.services;

import com.cap.pricecontrolcenter.domain.model.PriceModel;
import com.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;
import com.cap.pricecontrolcenter.infraestructure.exception.custom.PriceCreationException;
import com.cap.pricecontrolcenter.uils.TestHelper;
import com.cap.pricecontrolcenter.uils.TestInputHelper;
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
    public void case_001_createSuccessfullyCreated() {
        //Given
        PriceCommand command = TestHelper.generateDefaultCommand();
        PriceModel priceModel = PriceModel.builder().build();
        when(this.priceRepositoryPort.save(any())).thenReturn(Optional.of(priceModel));

        // Act
        PriceModel createdPriceModel = this.priceService.create(command);

        // Assert
        assertNotNull(createdPriceModel);
    }

    @Test
    public void case_002_createUnsuccessfulSave() {
        //Given
        PriceCommand command = TestHelper.generateDefaultCommand();
        when(this.priceRepositoryPort.save(any())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PriceCreationException.class, () -> this.priceService.create(command));
    }

    @Test
    public void case_003_findBrandAndProductToApply() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = TestInputHelper.RequestPriceController.PRODUCT_ID;
        Integer brandId = TestInputHelper.RequestPriceController.BRAND_ID;
        List<PriceModel> expectedPrices = Collections.singletonList(PriceModel.builder().build());
        when(this.priceRepositoryPort.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId))
                .thenReturn(Optional.of(expectedPrices));

        // Act
        Optional<PriceModel> result = this.priceService.findBrandAndProductToApply(applicationDate, productId, brandId);

        // Assert
        assertEquals(Optional.of(expectedPrices.get(0)), result);
    }

    @Test
    public void case_004_findBrandAndProductToApplyNoFoundPriceEntity() {
        // given
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = TestInputHelper.RequestPriceController.PRODUCT_ID;
        Integer brandId = TestInputHelper.RequestPriceController.BRAND_ID;
        when(this.priceRepositoryPort.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId))
                .thenReturn(Optional.empty());
        // Act
        Optional<PriceModel> result = this.priceService.findBrandAndProductToApply(applicationDate, productId, brandId);

        // Assert
        assertTrue(result.isEmpty(), "Expected empty Optional");
    }


}