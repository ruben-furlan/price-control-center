package com.test.cap.pricecontrolcenter.infraestructure.adapter.in.web.controller;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.dto.ResponsePriceDTO;
import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.mapper.PriceMapper;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
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
    public ResponseEntity<ResponsePriceDTO> create(@Valid @RequestBody() PriceCommand priceCommand) {

        PriceModel priceModel = this.priceUserCase.create(priceCommand);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.priceMapper.fullResponsePriceDTO(priceModel));
    }

    @GetMapping()
    public ResponseEntity<ResponsePriceDTO> findBrandAndProductToApply(@RequestParam(name = "application_date", required = true) LocalDateTime applicationDate,
                                                               @RequestParam(name = "product_id", required = true) Integer productId,
                                                               @RequestParam(name = "brand_id", required = true) Integer brandId) {

        Optional<PriceModel> priceModelOpt = this.priceUserCase.findBrandAndProductToApply(applicationDate, productId, brandId);

        return priceModelOpt.map(this.priceMapper::lightResponsePriceDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
}