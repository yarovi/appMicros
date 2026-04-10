package org.yasmani.io.bookingmicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "stock-microservice",
fallback = StockClientFallback.class)
public interface StockClient {


  @RequestMapping("/api/v1/stocks/{code}")
  boolean stockAvailable(String code);

}
