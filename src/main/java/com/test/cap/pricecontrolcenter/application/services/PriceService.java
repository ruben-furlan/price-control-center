package com.test.cap.pricecontrolcenter.application.services;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
import com.test.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;

public class PriceService implements PriceUserCase {

    private PriceRepositoryPort priceRepositoryPort;

    @Override
    public PriceModel create(PriceCommand command) {
        PriceModel priceModel = PriceModel.builder()
                .brandId(command.brandId())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .priceList(command.priceList())
                .productId(command.productId())
                .priority(command.priority())
                .price(command.price())
                .currency(command.currency())
                .build();

        return this.priceRepositoryPort.save(priceModel);
    }

}
