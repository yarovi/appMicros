package org.yasmani.io.bookingmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yasmani.io.bookingmicroservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
