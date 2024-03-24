package com.cap.pricecontrolcenter.infraestructure.adapter.in.web.controller;


import com.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceUserCase priceUserCase;

    @MockBean
    private PriceMapper priceMapper;


    @Test
    void case_001testCreatePrice_ok() throws Exception {
        // Given
        String validRequestJson = "{\"brand_id\":11,\"start_date\":\"2024-03-21T12:00:00\",\"end_date\":\"2024-03-22T12:00:00\",\"price_list\":1,\"product_id\":1,\"priority\":5,\"price\":10.5,\"currency\":\"USD\"}";

        // When/Then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/price-control-center")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequestJson)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void case_002_testCreatePrice_InvalidRequest() throws Exception {
        // Given
        String invalidRequestJson = "{\"brandId\":null,\"startDate\":\"2024-03-21T12:00:00\",\"endDate\":\"2024-03-22T12:00:00\",\"priceList\":1,\"productId\":1,\"priority\":5,\"price\":10.5,\"currency\":\"USD\"}";

        // When/Then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/price-control-center")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestJson)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value("El ID de la marca no puede ser nulo"));
    }


}