package com.test.cap.pricecontrolcenter.infraestructure.adapter.out.repositorty;

import com.test.cap.pricecontrolcenter.infraestructure.adapter.out.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaPrice extends JpaRepository<PricesEntity, Long> {
}
