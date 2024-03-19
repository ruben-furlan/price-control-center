package com.cap.pricecontrolcenter.domain.port.in;

import com.cap.pricecontrolcenter.domain.model.PriceModel;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Interfaz que define casos de uso relacionados con el precio.
 */
public interface PriceUserCase {

    /**
     * Crea un nuevo modelo de precio basado en el comando proporcionado.
     *
     * @param priceModel El comando para crear el modelo de precio.
     * @return El modelo de precio creado.
     */
    PriceModel create(PriceCommand priceModel);

    /**
     * Encuentra el modelo de precio correspondiente a una marca y producto para aplicar en una fecha determinada.
     *
     * @param applicationDate La fecha en la que se desea aplicar el precio.
     * @param productId       El identificador del producto.
     * @param brandId         El identificador de la marca.
     * @return Un objeto Optional que contiene el modelo de precio
     */
    Optional<PriceModel> findBrandAndProductToApply(LocalDateTime applicationDate, Integer productId, Integer brandId);

}
