package com.cap.pricecontrolcenter.domain.port.out;

import com.cap.pricecontrolcenter.domain.model.PriceModel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * Interfaz que define los puertos para la manipulación de precios en el repositorio.
 */
public interface PriceRepositoryPort {

    /**
     * Guarda un modelo de precio en el repositorio.
     *
     * @param price El modelo de precio a guardar.
     * @return Un objeto Optional que puede contener el modelo de precio guardado, si es exitoso, o vacío si falla.
     */
    Optional<PriceModel> save(PriceModel price);

    /**
     * Busca modelos de precio para una fecha de aplicación, producto y marca específicos, ordenados por prioridad .
     *
     * @param applicationDate La fecha en la que se desea aplicar el precio.
     * @param productId       El identificador del producto.
     * @param brandId         El identificador de la marca.
     * @return Un objeto Optional que puede contener una lista de modelos de precio encontrados
     */
    Optional<List<PriceModel>> findByDateProductAndBrandOrderByPriorityDesc(LocalDateTime applicationDate, Integer productId, Integer brandId);
}