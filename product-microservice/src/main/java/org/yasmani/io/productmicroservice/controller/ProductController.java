package org.yasmani.io.productmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yasmani.io.productmicroservice.entity.ProductEntity;
import org.yasmani.io.productmicroservice.repository.ProductRepository;


import java.util.List;
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }

    //implement  createProduct  method
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduct(@RequestBody ProductEntity productEntity){
        return productRepository.save(productEntity);
    }
    // generate example request to create a product
    /*
{
    "productName": "Product 1",
    "productDescription": "This is product 1",
    "unitPrice": 10.0
}
     */


}
