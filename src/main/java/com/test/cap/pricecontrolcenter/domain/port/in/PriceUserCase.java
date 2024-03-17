package com.test.cap.pricecontrolcenter.domain.port.in;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceUserCase {
    PriceModel create (PriceCommand priceModel);
    public Optional<PriceModel> findProductToApply(LocalDateTime applicationDate, Integer productId, Integer brandId);

}
