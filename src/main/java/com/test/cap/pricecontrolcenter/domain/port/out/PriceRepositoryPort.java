package com.test.cap.pricecontrolcenter.domain.port.out;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<PriceModel> save(PriceModel price);

    Optional<List<PriceModel>>  findByDateProductAndBrandOrderByPriorityDesc(LocalDateTime applicationDate, Integer productId, Integer brandId);


}
