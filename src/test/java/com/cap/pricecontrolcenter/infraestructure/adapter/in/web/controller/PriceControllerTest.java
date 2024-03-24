package com.cap.pricecontrolcenter.infraestructure.adapter.in.web.controller;


import com.cap.pricecontrolcenter.domain.model.PriceModel;
import com.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.dto.ResponsePriceDTO;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.mapper.PriceMapper;
import com.cap.pricecontrolcenter.uils.TestHelper;
import com.cap.pricecontrolcenter.uils.TestInputHelper;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceUserCase priceUserCase;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceController priceController;

    @Test
    public void case_001_createValidPriceCommandReturnsCreatedResponse() {
        // Given
        PriceCommand priceCommand = TestHelper.generateDefaultCommand();
        LocalDateTime now = LocalDateTime.now();
        PriceModel priceModel = TestHelper.generatePriceModelWithStartAndEndDate(now, now.plusDays(1));
        when(this.priceUserCase.create(priceCommand)).thenReturn(priceModel);
        when(this.priceMapper.fullResponsePriceDTO(priceModel)).thenReturn(TestHelper.generateResponseDTOWithStartAndEndDate(now, now.plusDays(1), 1));

        // When
        ResponseEntity<ResponsePriceDTO> response = priceController.create(priceCommand);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(this.priceUserCase, times(1)).create(priceCommand);
        verify(this.priceMapper, times(1)).fullResponsePriceDTO(priceModel);
    }

    @Test
    public void case_002_findBrandAndProductToApplyValidParametersReturnsOkResponseWithPriceDTO() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 12345;
        Integer brandId = 1;
        LocalDateTime now = LocalDateTime.now();
        PriceModel priceModel = TestHelper.generatePriceModelWithStartAndEndDate(now, now.plusDays(1));
        when(this.priceUserCase.findBrandAndProductToApply(applicationDate, productId, brandId)).thenReturn(Optional.of(priceModel));
        when(this.priceMapper.lightResponsePriceDTO(priceModel)).thenReturn(TestHelper.generateResponseDTOWithStartAndEndDate(now, now.plusDays(1), 1));

        // When
        ResponseEntity<ResponsePriceDTO> response = this.priceController.findBrandAndProductToApply(applicationDate, productId, brandId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(this.priceUserCase, times(1)).findBrandAndProductToApply(applicationDate, productId, brandId);
        verify(this.priceMapper, times(1)).lightResponsePriceDTO(priceModel);
    }

    @Test
    public void case_003_findBrandAndProductToApplyNoPriceFoundReturnsNoContentResponse() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.now();
        when(this.priceUserCase.findBrandAndProductToApply(applicationDate, TestInputHelper.RequestPriceController.PRODUCT_ID, TestInputHelper.RequestPriceController.BRAND_ID)).thenReturn(Optional.empty());

        // When
        ResponseEntity<ResponsePriceDTO> response = this.priceController.findBrandAndProductToApply(applicationDate, TestInputHelper.RequestPriceController.PRODUCT_ID, TestInputHelper.RequestPriceController.BRAND_ID);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(this.priceUserCase, times(1)).findBrandAndProductToApply(applicationDate, TestInputHelper.RequestPriceController.PRODUCT_ID, TestInputHelper.RequestPriceController.BRAND_ID);
    }


}
