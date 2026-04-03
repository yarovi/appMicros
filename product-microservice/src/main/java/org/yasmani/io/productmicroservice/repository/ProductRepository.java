package org.yasmani.io.productmicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.yasmani.io.productmicroservice.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity,String> {
}
