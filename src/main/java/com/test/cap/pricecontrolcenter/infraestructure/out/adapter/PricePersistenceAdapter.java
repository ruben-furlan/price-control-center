package com.test.cap.pricecontrolcenter.infraestructure.out.adapter;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;

import java.util.List;
import java.util.Optional;

public class PricePersistenceAdapter implements PriceRepositoryPort {
    @Override
    public PriceModel save(PriceModel task) {
        return null;
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
