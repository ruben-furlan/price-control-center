package com.test.cap.pricecontrolcenter.domain.port.out;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;

import java.util.List;
import java.util.Optional;

public interface PriceRepositoryPort {

    PriceModel save(PriceModel price);

    Optional<PriceModel> findById(PriceModel id);

    List<PriceModel> findAll();

    Optional<PriceModel> update(PriceModel price);

    boolean deleteById(Long id);

}
