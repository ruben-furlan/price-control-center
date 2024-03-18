package com.cap.pricecontrolcenter.infraestructure.adapter.out.mapper;

import com.cap.pricecontrolcenter.domain.model.PriceModel;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.dto.ResponsePriceDTO;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.entity.PricesEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {


    public List<PriceModel> ToPriceModel(List<PricesEntity> entities){
       return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

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
    public ResponsePriceDTO fullResponsePriceDTO(PriceModel model) {
        return ResponsePriceDTO.builder()
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


    public ResponsePriceDTO lightResponsePriceDTO(PriceModel model) {
        return ResponsePriceDTO.builder()
                    .brandId(model.getBrandId())
                    .priceList(model.getPriceList())
                    .productId(model.getProductId())
                    .startDate(model.getStartDate())
                    .endDate(model.getEndDate())
                    .price(model.getPrice())
                .build();
    }
}
