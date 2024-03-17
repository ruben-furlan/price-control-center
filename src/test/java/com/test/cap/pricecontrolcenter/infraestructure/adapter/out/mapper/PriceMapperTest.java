package com.test.cap.pricecontrolcenter.infraestructure.adapter.out.mapper;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.dto.ResponsePriceDTO;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.entity.PricesEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class PriceMapperTest {

    private final PriceMapper priceMapper = new PriceMapper();

    @Test
    void testToPriceModel() {
        // Arrange
        PricesEntity entity = PricesEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();
        List<PricesEntity> entities = Collections.singletonList(entity);

        // Act
        List<PriceModel> priceModels = this.priceMapper.ToPriceModel(entities);

        // Assert
        assertEquals(1, priceModels.size());
        assertEquals(entity.getBrandId(), priceModels.get(0).getBrandId());
        assertEquals(entity.getStartDate(), priceModels.get(0).getStartDate());
        assertEquals(entity.getEndDate(), priceModels.get(0).getEndDate());
        assertEquals(entity.getPriceList(), priceModels.get(0).getPriceList());
        assertEquals(entity.getProductId(), priceModels.get(0).getProductId());
        assertEquals(entity.getPriority(), priceModels.get(0).getPriority());
        assertEquals(entity.getPrice(), priceModels.get(0).getPrice());
        assertEquals(entity.getCurrency(), priceModels.get(0).getCurrency());
    }

    @Test
    void testToEntity() {
        // Arrange
        PriceModel model = PriceModel.builder()
                .brandId(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();

        // Act
        PricesEntity entity = this.priceMapper.toEntity(model);

        // Assert
        assertEquals(model.getBrandId(), entity.getBrandId());
        assertEquals(model.getStartDate(), entity.getStartDate());
        assertEquals(model.getEndDate(), entity.getEndDate());
        assertEquals(model.getPriceList(), entity.getPriceList());
        assertEquals(model.getProductId(), entity.getProductId());
        assertEquals(model.getPriority(), entity.getPriority());
        assertEquals(model.getPrice(), entity.getPrice());
        assertEquals(model.getCurrency(), entity.getCurrency());
    }

    @Test
    void testToModel() {
        // Arrange
        PricesEntity entity = PricesEntity.builder()
                .brandId(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();

        // Act
        PriceModel model = this.priceMapper.toModel(entity);

        // Assert
        assertEquals(entity.getBrandId(), model.getBrandId());
        assertEquals(entity.getStartDate(), model.getStartDate());
        assertEquals(entity.getEndDate(), model.getEndDate());
        assertEquals(entity.getPriceList(), model.getPriceList());
        assertEquals(entity.getProductId(), model.getProductId());
        assertEquals(entity.getPriority(), model.getPriority());
        assertEquals(entity.getPrice(), model.getPrice());
        assertEquals(entity.getCurrency(), model.getCurrency());
    }

    @Test
    void testFullResponsePriceDTO() {
        // Arrange
        PriceModel model = PriceModel.builder()
                .brandId(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();

        // Act
        ResponsePriceDTO responsePriceDTO = this.priceMapper.fullResponsePriceDTO(model);

        // Assert
        assertEquals(model.getBrandId(), responsePriceDTO.getBrandId());
        assertEquals(model.getStartDate(), responsePriceDTO.getStartDate());
        assertEquals(model.getEndDate(), responsePriceDTO.getEndDate());
        assertEquals(model.getPriceList(), responsePriceDTO.getPriceList());
        assertEquals(model.getProductId(), responsePriceDTO.getProductId());
        assertEquals(model.getPriority(), responsePriceDTO.getPriority());
        assertEquals(model.getPrice(), responsePriceDTO.getPrice());
        assertEquals(model.getCurrency(), responsePriceDTO.getCurrency());
    }

    @Test
    void testLightResponsePriceDTO() {
        // Arrange
        PriceModel model = PriceModel.builder()
                .brandId(1)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .priceList(1)
                .productId(35455)
                .price(BigDecimal.valueOf(35.50))
                .build();

        // Act
        ResponsePriceDTO responsePriceDTO = this.priceMapper.lightResponsePriceDTO(model);

        // Assert
        assertEquals(model.getBrandId(), responsePriceDTO.getBrandId());
        assertEquals(model.getStartDate(), responsePriceDTO.getStartDate());
        assertEquals(model.getEndDate(), responsePriceDTO.getEndDate());
        assertEquals(model.getPriceList(), responsePriceDTO.getPriceList());
        assertEquals(model.getProductId(), responsePriceDTO.getProductId());
        assertEquals(model.getPrice(), responsePriceDTO.getPrice());
    }
}
