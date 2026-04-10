package org.yasmani.io.stockmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yasmani.io.stockmicroservice.entity.StockEntity;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity,Long> {

  Optional<StockEntity> findByCode(String code);
}
