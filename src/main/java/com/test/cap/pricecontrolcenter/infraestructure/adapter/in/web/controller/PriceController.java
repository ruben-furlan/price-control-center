package com.test.cap.pricecontrolcenter.infraestructure.adapter.in.web.controller;

import com.test.cap.pricecontrolcenter.domain.model.PriceModel;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceCommand;
import com.test.cap.pricecontrolcenter.domain.port.in.PriceUserCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/price-control-center")
@RequiredArgsConstructor
public class PriceController {
    private final PriceUserCase priceUserCase;
    @PostMapping()
    public ResponseEntity<PriceModel> create(@Valid @RequestBody() PriceCommand priceCommand) {
        PriceModel response = this.priceUserCase.create(priceCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}