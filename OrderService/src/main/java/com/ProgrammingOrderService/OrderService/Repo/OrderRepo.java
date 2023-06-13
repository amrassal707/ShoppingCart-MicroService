package com.ProgrammingOrderService.OrderService.Repo;

import com.ProgrammingOrderService.OrderService.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}
