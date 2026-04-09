package org.yasmani.io.productmicroservice.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Setter
@Getter
@RestController
@RequestMapping("/api/v1/category" )

public class CategoryController {

  @Value("${app.testProp}")
  private String testProp;
  @GetMapping("test-prop")
  public String getTestProp() {
    return testProp;
  }
}
