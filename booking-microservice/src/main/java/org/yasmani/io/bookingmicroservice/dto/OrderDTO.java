package org.yasmani.io.bookingmicroservice.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yasmani.io.bookingmicroservice.entity.OrderItem;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

  private List<OrderItem> orderItemList;
}
