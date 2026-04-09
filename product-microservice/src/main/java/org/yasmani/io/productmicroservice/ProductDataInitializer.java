package org.yasmani.io.productmicroservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.yasmani.io.productmicroservice.entity.ProductEntity;

import java.util.Arrays;
import java.util.List;
@Slf4j
@Component
public class ProductDataInitializer implements CommandLineRunner {
  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void run(String... args) {
    // Verificar si ya hay datos
    if (mongoTemplate.count(new Query(), ProductEntity.class) == 0) {
      List<ProductEntity> products = Arrays.asList(
          new ProductEntity(null, "Laptop Gaming", "High performance gaming laptop", 1299.99),
          new ProductEntity(null, "Wireless Mouse", "Ergonomic wireless mouse", 29.99),
          new ProductEntity(null, "Mechanical Keyboard", "RGB mechanical keyboard", 89.99)
      );

      mongoTemplate.insert(products, ProductEntity.class);
      log.info("✅ Cargados {} productos iniciales", products.size());
    } else {
      log.info("ℹ️ Los productos ya existen, omitiendo carga inicial");
    }
  }
}
