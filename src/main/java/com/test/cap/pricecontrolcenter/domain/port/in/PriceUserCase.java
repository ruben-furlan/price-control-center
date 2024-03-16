package com.test.cap.pricecontrolcenter.domain.port.in;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceCommand;

public interface PriceUserCase {
    PriceModel create (PriceCommand priceModel);

}
