package com.test.cap.pricecontrolcenter.infraestructure.exception;

import com.test.cap.pricecontrolcenter.infraestructure.exception.custom.PriceCreationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Mock
    private MissingServletRequestParameterException missingServletRequestParameterException;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;


    @Test
    void handleMissingServletRequestParameterException() {
        // Given
        String parameterName = "parameterName";
        String expectedMessage = "Missing request parameters: " + parameterName;
        Mockito.when(this.missingServletRequestParameterException.getParameterName()).thenReturn(parameterName);

        // Act
        ResponseEntity<String> responseEntity = this.globalExceptionHandler.handleMissingServletRequestParameterException(missingServletRequestParameterException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedMessage, responseEntity.getBody());
    }

    @Test
    void handlePriceCreationException() {
        // Given
        PriceCreationException priceCreationException = new PriceCreationException();

        // Act
        ResponseEntity<String> responseEntity =this. globalExceptionHandler.handlePriceCreationException(priceCreationException);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An error occurred while creating the price.", responseEntity.getBody());
    }
}