package com.ProgrammingOrderService.OrderService.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "order_items")
@Data
public class orderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;
    private BigDecimal price;
    private Integer quantity;



}
