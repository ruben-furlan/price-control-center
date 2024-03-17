package com.test.cap.pricecontrolcenter.infraestructure.adapter.out;

import com.test.cap.pricecontrolcenter.common.PersistenceAdapter;
import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.entity.PricesEntity;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.mapper.PriceMapper;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.repositorty.SpringDataJpaPrice;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceRepositoryPort {
    private final SpringDataJpaPrice springDataJpaPrice;
    private final PriceMapper priceMapper;

    @Override
    public Optional<PriceModel> save(PriceModel task) {
        PricesEntity pricesEntity = this.priceMapper.toEntity(task);
        PricesEntity save = this.springDataJpaPrice.save(pricesEntity);
        return  Optional.ofNullable(this.priceMapper.toModel(save));
    }

    @Override
    public  Optional<List<PriceModel>>  findByDateProductAndBrandOrderByPriorityDesc(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        List<PricesEntity> pricesEntities = this.springDataJpaPrice.findByDateProductAndBrandOrderByPriorityDesc(applicationDate,productId,brandId);
        return  Optional.ofNullable( this.priceMapper.ToPriceModel(pricesEntities));
    }


}
