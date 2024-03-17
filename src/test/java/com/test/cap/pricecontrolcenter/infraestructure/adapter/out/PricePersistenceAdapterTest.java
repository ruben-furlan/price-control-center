package com.test.cap.pricecontrolcenter.infraestructure.adapter.out;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.entity.PricesEntity;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.mapper.PriceMapper;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.repositorty.SpringDataJpaPrice;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PricePersistenceAdapterTest {

    @Mock
    private SpringDataJpaPrice springDataJpaPrice;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PricePersistenceAdapter pricePersistenceAdapter;

    @Test
    void save_SuccessfullySaved() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        PriceModel priceModel = PriceModel.builder()
                .brandId(1)
                .startDate(now)
                .endDate(now.plusDays(1))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();
        PricesEntity pricesEntity = PricesEntity.builder()
                .brandId(1)
                .startDate(now)
                .endDate(now.plusDays(1))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build();

        when(this.priceMapper.toEntity(priceModel)).thenReturn(pricesEntity);
        when(this.springDataJpaPrice.save(pricesEntity)).thenReturn(pricesEntity);
        when(this.priceMapper.toModel(pricesEntity)).thenReturn(priceModel);

        // Act
        Optional<PriceModel> savedPriceModel = pricePersistenceAdapter.save(priceModel);

        // Assert
        assertEquals(Optional.of(priceModel), savedPriceModel);
        verify(this.priceMapper, times(1)).toEntity(priceModel);
        verify(this.springDataJpaPrice, times(1)).save(pricesEntity);
        verify(this.priceMapper, times(1)).toModel(pricesEntity);
    }

    @Test
    void findByDateProductAndBrandOrderByPriorityDesc_FoundPrices() {
        // Given
        LocalDateTime startDate = LocalDateTime.of(2022, 3, 17, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 3, 18, 12, 0);
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;
        List<PricesEntity> pricesEntities = Collections.singletonList((PricesEntity.builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build()));
        List<PriceModel> expectedPriceModels = Collections.singletonList(PriceModel.builder()
                .brandId(1)
                .startDate(startDate)
                .endDate(endDate)
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(BigDecimal.valueOf(35.50))
                .currency("EUR")
                .build());
        when(this.springDataJpaPrice.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId))
                .thenReturn(pricesEntities);
        when(this.priceMapper.ToPriceModel(pricesEntities)).thenReturn(expectedPriceModels);

        // Act
        Optional<List<PriceModel>> foundPriceModels = pricePersistenceAdapter.findByDateProductAndBrandOrderByPriorityDesc(
                applicationDate, productId, brandId);

        // Assert
        assertEquals(Optional.of(expectedPriceModels), foundPriceModels);
        assertEquals(pricesEntities.size(), expectedPriceModels.size());
        IntStream.range(0, pricesEntities.size())
                .forEach(i -> {
                    assertEquals(pricesEntities.get(i).getBrandId(), expectedPriceModels.get(i).getBrandId());
                    assertEquals(pricesEntities.get(i).getStartDate(), expectedPriceModels.get(i).getStartDate());
                    assertEquals(pricesEntities.get(i).getEndDate(), expectedPriceModels.get(i).getEndDate());
                    assertEquals(pricesEntities.get(i).getPriceList(), expectedPriceModels.get(i).getPriceList());
                    assertEquals(pricesEntities.get(i).getProductId(), expectedPriceModels.get(i).getProductId());
                    assertEquals(pricesEntities.get(i).getPriority(), expectedPriceModels.get(i).getPriority());
                    assertEquals(pricesEntities.get(i).getPrice(), expectedPriceModels.get(i).getPrice());
                    assertEquals(pricesEntities.get(i).getCurrency(), expectedPriceModels.get(i).getCurrency());
                });
        verify(this.springDataJpaPrice, times(1)).findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);
        verify(this.priceMapper, times(1)).ToPriceModel(pricesEntities);
    }
}
