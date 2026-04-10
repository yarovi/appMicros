package org.yasmani.io.bookingmicroservice.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockClientFallback implements StockClient {
  @Override
  public boolean stockAvailable(String code) {
    log.error("⚠ Circuit Breaker activado para stock-microservice. Code: {}", code);
    return false;
  }
}
