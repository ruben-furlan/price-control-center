package com.cap.pricecontrolcenter.infraestructure.adapter.in.web.controller;

import com.cap.pricecontrolcenter.PriceControlCenterApplication;
import com.cap.pricecontrolcenter.uils.TestInputHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void case_001_testPriceController() throws Exception {
        // Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-14T10:00:00", 35455, 1,1,BigDecimal.valueOf(35.50));
    }
    @Test
    public void  case_002_testPriceController() throws Exception {
        // Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-14T16:00:00", 35455, 1,2,BigDecimal.valueOf(25.45));
    }

    @Test
    public void  case_003_testPriceController() throws Exception {
        // Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-14T21:00:00", 35455, 1,1,BigDecimal.valueOf(35.50));
    }
    @Test
    public void  case_004_testPriceController() throws Exception {
        // Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-15T10:00:00", 35455, 1,3,BigDecimal.valueOf(30.50));

    }
    @Test
    public void  case_005_testPriceController() throws Exception {
        // Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        performAndVerify("2020-06-16T21:00:00", 35455, 1,4,BigDecimal.valueOf(38.95));
    }

    @Test
    public void case_006_badRequestBranId() throws Exception {
        //Given/When/Then
        performAndVerifyBadRequest("$.brandId","El ID de la marca no puede ser nulo",TestInputHelper.RequestPriceController.BRAND_ID_NULL);
    }

    @Test
    public void case_007_badRequestStartDate() throws Exception {
        //Given/When/Then
        performAndVerifyBadRequest("$.startDate","La fecha de inicio no puede ser nula",TestInputHelper.RequestPriceController.START_DATE_NULL);
    }

    @Test
    public void case_006_badRequestEndDate() throws Exception {
        //Given/When/Then
        performAndVerifyBadRequest("$.endDate","La fecha de fin no puede ser nula",TestInputHelper.RequestPriceController.END_DATE_NULL);
    }

    @Test
    public void case_010_badRequestPriority() throws Exception {
        //Given/When/Then
        performAndVerifyBadRequest("$.priority","La prioridad no puede ser nula", TestInputHelper.RequestPriceController.PRIORITY_NULL);
    }

    @Test
    public void case_011_badRequestPriorityMin() throws Exception {
        //Given/When/Then
        performAndVerifyBadRequest("$.priority","La prioridad debe ser mayor o igual a 1",TestInputHelper.RequestPriceController.PRIORITY_MIN);
    }

    @Test
    public void case_012_badRequestPriorityMaX() throws Exception {
        //Given/When/Then
        performAndVerifyBadRequest("$.priority", "La prioridad debe ser menor o igual a 10",TestInputHelper.RequestPriceController.PRIORITY_MAX);
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
                .andExpect(jsonPath("$.price").value(price))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    private void performAndVerifyBadRequest(String jsonPath,String expectedMessage,String content) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/price-control-center")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath(jsonPath).value(expectedMessage));
    }

}
