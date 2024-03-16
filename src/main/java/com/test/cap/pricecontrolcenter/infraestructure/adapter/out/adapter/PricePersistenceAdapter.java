package com.test.cap.pricecontrolcenter.infraestructure.out.adapter;

import com.test.cap.pricecontrolcenter.common.PersistenceAdapter;
import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;
import com.test.cap.pricecontrolcenter.infraestructure.out.entity.PricesEntity;
import com.test.cap.pricecontrolcenter.infraestructure.out.mapper.PriceMapper;
import com.test.cap.pricecontrolcenter.infraestructure.out.repositorty.SpringDataJpaPrice;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceRepositoryPort {
    private SpringDataJpaPrice springDataJpaPrice;
    private PriceMapper priceMapper;

    @Override
    public PriceModel save(PriceModel task) {
        PricesEntity pricesEntity = this.priceMapper.toEntity(task);
        PricesEntity save = this.springDataJpaPrice.save(pricesEntity);
        return this.priceMapper.toModel(save);
    }

    @Override
    public Optional<PriceModel> findById(PriceModel id) {
        return Optional.empty();
    }

    @Override
    public List<PriceModel> findAll() {
        return null;
    }

    @Override
    public Optional<PriceModel> update(PriceModel task) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}