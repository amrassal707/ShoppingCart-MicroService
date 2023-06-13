package com.ProgrammingOrderService.OrderService.Service;

import com.ProgrammingOrderService.OrderService.DTO.InventoryResponse;
import com.ProgrammingOrderService.OrderService.DTO.orderLineItemsDTO;
import com.ProgrammingOrderService.OrderService.DTO.orderRequest;
import com.ProgrammingOrderService.OrderService.Entity.Order;
import com.ProgrammingOrderService.OrderService.Entity.orderLineItems;
import com.ProgrammingOrderService.OrderService.Repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import com.ProgrammingOrderService.OrderService.Entity.orderLineItems;
@Service
@RequiredArgsConstructor
public class orderService {
    private final OrderRepo orderRepo;
    private final WebClient.Builder webClientBuilder;



    public void placeOrder(orderRequest orderRequest) {
        Order order= new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<orderLineItems> orderLineItems = orderRequest.getOrderLineItemsDTOS().stream().map((this::mapToDTO)).toList();
        order.setOrderLineItems(orderLineItems);
        List <String> skuCodes= order.getOrderLineItems().stream().map(com.ProgrammingOrderService.OrderService.Entity.orderLineItems::getSkuCode).toList();
        // Call inventory Service and place order if order in stock

        // we provide the mapping type of get since we call the get method from inventorycontroller
        // we provide the url after that and retrieve the data /
        // we identify the data by bodytoMono which will work with the returned datatype of Boolean
        // the .block lets the webclient know that this is a synchronized call
        InventoryResponse [] inventoryResponses = webClientBuilder.build().get().uri("http://InventoryService/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build()).retrieve()
                .bodyToMono(InventoryResponse[].class).block();

       boolean allProductinStock= Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);
        if (allProductinStock)
        orderRepo.save(order);
        else
            throw new IllegalArgumentException("Product is not in stock please try again later");
    }
    private orderLineItems mapToDTO(orderLineItemsDTO orderLineItemsDTO) {
        orderLineItems orderLineItems= new orderLineItems();
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        return orderLineItems;
    }
}
