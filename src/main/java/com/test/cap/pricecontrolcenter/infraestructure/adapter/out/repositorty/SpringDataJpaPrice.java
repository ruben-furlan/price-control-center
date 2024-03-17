package com.test.cap.pricecontrolcenter.infraestructure.adapter.out.repositorty;

import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.entity.PricesEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaPrice extends JpaRepository<PricesEntity, Long> {
    String PRICE_BY_DATE_PRODUCT_AND_BRAND_QUERY =
            "SELECT p FROM PricesEntity p " +
                    "WHERE :applicationDate BETWEEN p.startDate AND p.endDate " +
                    "AND p.productId = :productId " +
                    "AND p.brandId = :brandId " +
                    "ORDER BY p.priority DESC";

    @Query(PRICE_BY_DATE_PRODUCT_AND_BRAND_QUERY)
    List<PricesEntity> findByDateProductAndBrandOrderByPriorityDesc(
            LocalDateTime applicationDate, Integer productId, Integer brandId);

}
