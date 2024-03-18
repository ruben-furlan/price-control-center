package com.cap.pricecontrolcenter.domain.port.in;

import com.cap.pricecontrolcenter.domain.model.PriceModel;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceUserCase {
    PriceModel create (PriceCommand priceModel);
    Optional<PriceModel> findBrandAndProductToApply(LocalDateTime applicationDate, Integer productId, Integer brandId);

}
