package org.yasmani.io.stockmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yasmani.io.stockmicroservice.entity.StockEntity;
import org.yasmani.io.stockmicroservice.repository.StockRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {

  @Autowired
  private StockRepository repository;

  public boolean stockAvailable(@PathVariable  String code) {
    Optional<StockEntity> stock= repository.findByCode(code);
    stock.orElseThrow(()-> new RuntimeException("Stock not found for code: "+code));
    return stock.get().getQuantity()>0;
  }
}
