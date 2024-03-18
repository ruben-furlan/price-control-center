package com.cap.pricecontrolcenter.application.services;

import com.cap.pricecontrolcenter.common.UseCase;
import com.cap.pricecontrolcenter.domain.model.PriceModel;
import com.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
import com.cap.pricecontrolcenter.domain.port.out.PriceRepositoryPort;
import com.cap.pricecontrolcenter.infraestructure.exception.custom.PriceCreationException;
import java.time.LocalDateTime;
import java.util.Optional;
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

        return this.priceRepositoryPort.save(priceModel).orElseThrow(PriceCreationException::new);
    }

    public Optional<PriceModel> findBrandAndProductToApply(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        return this.priceRepositoryPort
                .findByDateProductAndBrandOrderByPriorityDesc(applicationDate, productId, brandId)
                .flatMap(prices -> prices.stream().findFirst());
    }
}
