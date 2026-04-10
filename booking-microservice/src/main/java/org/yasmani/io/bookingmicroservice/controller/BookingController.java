package org.yasmani.io.bookingmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yasmani.io.bookingmicroservice.client.StockClient;
import org.yasmani.io.bookingmicroservice.dto.OrderDTO;
import org.yasmani.io.bookingmicroservice.entity.Order;
import org.yasmani.io.bookingmicroservice.repository.OrderRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private StockClient stockClient;


  public String saveOrder(@RequestBody OrderDTO orderDTO){

    boolean inStock = orderDTO.getOrderItemList()
        .stream().allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

    if (!inStock) {
      return "One or more items are out of stock. Please try again later.";
    }


    Order order = new Order();
    order.setOrderNro(UUID.randomUUID().toString());
    order.setOrderItems(orderDTO.getOrderItemList());

    orderRepository.save(order);

    return  "Order saved successfully with order number: " + order.getOrderNro();
  }

}
