package com.test.cap.pricecontrolcenter.infraestructure.exception.custom;

public class PriceCreationException extends RuntimeException {
    public PriceCreationException() {
        super("Error while creating the price");
    }
}