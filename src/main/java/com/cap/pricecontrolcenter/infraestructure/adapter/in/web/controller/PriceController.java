package com.cap.pricecontrolcenter.infraestructure.adapter.in.web.controller;

import com.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.dto.ResponsePriceDTO;
import com.cap.pricecontrolcenter.infraestructure.adapter.out.mapper.PriceMapper;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/price-control-center")
@RequiredArgsConstructor
public class PriceController {
    private final PriceUserCase priceUserCase;
    private final PriceMapper priceMapper;

    @PostMapping()
    public ResponseEntity<ResponsePriceDTO> create(@Valid @RequestBody PriceCommand priceCommand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.priceMapper.fullResponsePriceDTO(this.priceUserCase.create(priceCommand)));
    }

    @GetMapping()
    public ResponseEntity<ResponsePriceDTO> findBrandAndProductToApply(@RequestParam(name = "application_date") LocalDateTime applicationDate,
                                                                       @RequestParam(name = "product_id") Integer productId,
                                                                       @RequestParam(name = "brand_id") Integer brandId) {
        return this.priceUserCase.findBrandAndProductToApply(applicationDate, productId, brandId)
                .map(this.priceMapper::lightResponsePriceDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}