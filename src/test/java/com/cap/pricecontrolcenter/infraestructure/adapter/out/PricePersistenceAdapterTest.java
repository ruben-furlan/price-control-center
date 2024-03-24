package com.cap.pricecontrolcenter.infraestructure.adapter.out;

import com.cap.pricecontrolcenter.domain.model.PriceModel;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.entity.PricesEntity;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.mapper.PriceMapper;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.repositorty.SpringDataJpaPrice;
import com.cap.pricecontrolcenter.uils.TestHelper;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void case_001_save_successFullySaved() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        PriceModel priceModel = TestHelper.generatePriceModelWithStartAndEndDate(now, now.plusDays(1));
        PricesEntity pricesEntity = TestHelper.generatePriceEntityWithStartAndEndDate(now, now.plusDays(1));

        when(this.priceMapper.toEntity(priceModel)).thenReturn(pricesEntity);
        when(this.springDataJpaPrice.save(pricesEntity)).thenReturn(pricesEntity);
        when(this.priceMapper.toModel(pricesEntity)).thenReturn(priceModel);

        // Act
        Optional<PriceModel> savedPriceModel = this.pricePersistenceAdapter.save(priceModel);

        // Assert
        assertEquals(Optional.of(priceModel), savedPriceModel);
        verify(this.priceMapper, times(1)).toEntity(priceModel);
        verify(this.springDataJpaPrice, times(1)).save(pricesEntity);
        verify(this.priceMapper, times(1)).toModel(pricesEntity);
    }

    @Test
    public void case_002_save_andReturnEntityNotFound() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        PriceModel priceModel = TestHelper.generatePriceModelWithStartAndEndDate(now, now.plusDays(1));
        PricesEntity pricesEntity = TestHelper.generatePriceEntityWithStartAndEndDate(now, now.plusDays(1));

        when(this.priceMapper.toEntity(priceModel)).thenReturn(pricesEntity);
        when(this.springDataJpaPrice.save(pricesEntity)).thenReturn(null);

        // Act
        Optional<PriceModel> savedPriceModel = this.pricePersistenceAdapter.save(priceModel);

        // Assert
        assertEquals(Optional.empty(), savedPriceModel);
        verify(this.priceMapper, times(1)).toEntity(priceModel);
        verify(this.springDataJpaPrice, times(1)).save(pricesEntity);

    }

    @Test
    public void case_003_save_MappingCouldNotDone() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        PriceModel priceModel = TestHelper.generatePriceModelWithStartAndEndDate(now, now.plusDays(1));
        PricesEntity pricesEntity = TestHelper.generatePriceEntityWithStartAndEndDate(now, now.plusDays(1));

        when(this.priceMapper.toEntity(priceModel)).thenReturn(pricesEntity);
        when(this.springDataJpaPrice.save(pricesEntity)).thenReturn(pricesEntity);
        when(this.priceMapper.toModel(pricesEntity)).thenReturn(null);

        // Act
        Optional<PriceModel> savedPriceModel = this.pricePersistenceAdapter.save(priceModel);

        // Assert
        assertEquals(Optional.empty(), savedPriceModel);
        verify(this.priceMapper, times(1)).toEntity(priceModel);
        verify(this.springDataJpaPrice, times(1)).save(pricesEntity);
        verify(this.priceMapper, times(1)).toModel(pricesEntity);
    }


    @Test
    public void case_004_findByDateProductAndBrandOrderByPriorityDescFoundPrices() {
        // Given
        LocalDateTime startDate = LocalDateTime.of(2022, 3, 17, 12, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 3, 18, 12, 0);
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;
        List<PricesEntity> pricesEntities = TestHelper.generatePriceEntitiesWithStartAndEndDateList(startDate, endDate);
        List<PriceModel> expectedPriceModels = TestHelper.generatePriceModelWithStartAndEndDateList(startDate, endDate);

        when(this.springDataJpaPrice.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId)).thenReturn(pricesEntities);
        when(this.priceMapper.ToPriceModel(pricesEntities)).thenReturn(expectedPriceModels);

        // Act
        Optional<List<PriceModel>> foundPriceModels = this.pricePersistenceAdapter.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);

        // Assert
        assertEquals(Optional.of(expectedPriceModels), foundPriceModels);
        assertEquals(pricesEntities.size(), expectedPriceModels.size());
        IntStream.range(0, pricesEntities.size()).forEach(i -> TestHelper.assertPriceModelEqualsEntity(pricesEntities.get(i), expectedPriceModels.get(i)));
        verify(this.springDataJpaPrice, times(1)).findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);
        verify(this.priceMapper, times(1)).ToPriceModel(pricesEntities);
    }


    @Test
    public  void case_005_findByDateProductAndBrandOrderByPriorityDescDifferentPriorities() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;
        LocalDateTime now = LocalDateTime.now();
        //  entities with different priorities
        PricesEntity entityWithPriority0 = TestHelper.generatePriceEntityWithStartAndEndDateAndPriority(now, now.plusDays(1), 5);
        PricesEntity entityWithPriority1 = TestHelper.generatePriceEntityWithStartAndEndDateAndPriority(now, now.plusDays(1), 6);
        PricesEntity entityWithPriority2 = TestHelper.generatePriceEntityWithStartAndEndDateAndPriority(now, now.plusDays(1), 1);

        //  expected models with correct order based on priorities
        List<PriceModel> expectedPriceModels = Arrays.asList(
                this.priceMapper.toModel(entityWithPriority2),
                this.priceMapper.toModel(entityWithPriority1),
                this.priceMapper.toModel(entityWithPriority0)
        );

        // Mock the repository to return entities with different priorities
        List<PricesEntity> pricesEntities = Arrays.asList(entityWithPriority0, entityWithPriority1, entityWithPriority2);
        when(this.springDataJpaPrice.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId)).thenReturn(pricesEntities);
        when(this.priceMapper.ToPriceModel(pricesEntities)).thenReturn(expectedPriceModels);

        // Act
        Optional<List<PriceModel>> foundPriceModels = this.pricePersistenceAdapter.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);

        // Assert
        assertTrue(foundPriceModels.isPresent(), "Expected non-empty Optional");
        assertEquals(expectedPriceModels.size(), foundPriceModels.get().size(), "Size mismatch");
        IntStream.range(0, expectedPriceModels.size()).forEach(i -> {
            assertEquals(expectedPriceModels.get(i), foundPriceModels.get().get(i), "Model mismatch at index " + i);
        });
        verify(this.springDataJpaPrice, times(1)).findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);
        verify(this.priceMapper, times(1)).ToPriceModel(pricesEntities);
    }


    @Test
    public void case_006_findByDateProductAndBrandOrderByPriorityDescNotFoundPricesEntity() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;

        when(this.springDataJpaPrice.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId)).thenReturn(Collections.emptyList());

        // Act
        Optional<List<PriceModel>> foundPriceModels = this.pricePersistenceAdapter.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);


        // Assert
        assertTrue(foundPriceModels.isPresent(), "Expected non-empty Optional");
        assertTrue(foundPriceModels.get().isEmpty(), "Expected empty List inside Optional");
        verify(this.springDataJpaPrice, times(1)).findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);
    }


    @Test
    public void case_007_findByDateProductAndBrandOrderByPriorityDescNotFoundNullResponsePriceEntity() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;

        when(this.springDataJpaPrice.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId)).thenReturn(null);

        // Act
        Optional<List<PriceModel>> foundPriceModels = this.pricePersistenceAdapter.findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);

        // Assert
        assertTrue(foundPriceModels.isPresent(), "Expected non-empty Optional");
        assertTrue(foundPriceModels.get().isEmpty(), "Expected empty List inside Optional");
        verify(this.springDataJpaPrice, times(1)).findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId);
    }


}
