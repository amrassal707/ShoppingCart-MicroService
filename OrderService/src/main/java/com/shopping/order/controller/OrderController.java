package com.shopping.order.controller;

import com.shopping.order.dto.OrderRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shopping.order.service.OrderService;
@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController
{

    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name = "inventory" ,fallbackMethod = "returnThis")
    public ResponseEntity<Object> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("order is placed successfully");
    }

    @GetMapping


    public String returnThis(OrderRequest orderRequest, RuntimeException e) {
        log.error("system is down for order request {}",orderRequest);
        log.error("system with exception {}",e.getMessage());
        return "system is down try later, thank you";
    }

}
