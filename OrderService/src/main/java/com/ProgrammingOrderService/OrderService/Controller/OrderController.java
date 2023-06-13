package com.ProgrammingOrderService.OrderService.Controller;

import com.ProgrammingOrderService.OrderService.DTO.orderRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ProgrammingOrderService.OrderService.Service.orderService;
@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController
{

    private final orderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory" ,fallbackMethod = "returnThis")
    public String placeOrder(@RequestBody orderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "order placed";
    }

    public String returnThis(orderRequest orderRequest, RuntimeException e) {
        return "system is down try later, thank you";
    }

}
