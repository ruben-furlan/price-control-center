package com.test.cap.pricecontrolcenter.application.services;

import com.test.cap.pricecontrolcenter.common.UseCase;
import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
import com.test.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@UseCase
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class PriceService implements PriceUserCase {

    private final PriceRepositoryPort priceRepositoryPort;

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
