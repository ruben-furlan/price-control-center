package com.test.cap.pricecontrolcenter.infraestructure.adapter.in.web.controller;


import com.test.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
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

    @Test
    void testCreatePrice_InvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/price-control-center")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"brandId\":null,\"startDate\":\"2024-03-21T12:00:00\",\"endDate\":\"2024-03-22T12:00:00\",\"priceList\":1,\"productId\":1,\"priority\":5,\"price\":10.5,\"currency\":\"USD\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json("{\"brandId\":\"El ID de la marca no puede ser nulo\"}"));
    }
}