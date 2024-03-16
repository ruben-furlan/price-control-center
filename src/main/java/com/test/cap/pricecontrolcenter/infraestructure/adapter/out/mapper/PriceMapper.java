package com.test.cap.pricecontrolcenter.infraestructure.out.mapper;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.infraestructure.out.entity.PricesEntity;

public class PriceMapper {
    public  PricesEntity toEntity(PriceModel model) {
        return PricesEntity.builder()
                .brandId(model.getBrandId())
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .priceList(model.getPriceList())
                .productId(model.getProductId())
                .priority(model.getPriority())
                .price(model.getPrice())
                .currency(model.getCurrency())
                .build();
    }

    public  PriceModel toModel(PricesEntity entity) {
        return PriceModel.builder()
                .brandId(entity.getBrandId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .priceList(entity.getPriceList())
                .productId(entity.getProductId())
                .priority(entity.getPriority())
                .price(entity.getPrice())
                .currency(entity.getCurrency())
                .build();
    }
}
