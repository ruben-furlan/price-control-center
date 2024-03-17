package com.test.cap.pricecontrolcenter.infraestructure.adapter.in.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.cap.pricecontrolcenter.PriceControlCenterApplication;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.time.LocalDateTime;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PriceControlCenterApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PriceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPriceController() throws Exception {
        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-14T10:00:00", 35455, 1,1,BigDecimal.valueOf(35.50));

        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-14T16:00:00", 35455, 1,2,BigDecimal.valueOf(24.25));

        // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-14T21:00:00", 35455, 1,1,BigDecimal.valueOf(35.50));

        // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-15T10:00:00", 35455, 1,3,BigDecimal.valueOf(30.50));

        // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-16T21:00:00", 35455, 1,4,BigDecimal.valueOf(38.45));
    }

    private void performAndVerify(String applicationDate, Integer productId, Integer brandId, Integer priceList, BigDecimal price) throws Exception {
        MvcResult result = mockMvc.perform(get("/v1/price-control-center")
                        .param("application_date", applicationDate)
                        .param("product_id", productId.toString())
                        .param("brand_id", brandId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.product_id").value(productId))
                .andExpect(jsonPath("$.brand_id").value(brandId))
                .andExpect(jsonPath("$.price_list").value(priceList))
                .andExpect(jsonPath("$.start_date").isString())
                .andExpect(jsonPath("$.end_date").isString())
                .andExpect(jsonPath("$.price").isNumber())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
