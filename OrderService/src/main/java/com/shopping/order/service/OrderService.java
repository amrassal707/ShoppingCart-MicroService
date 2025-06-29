package com.shopping.order.service;

import com.shopping.order.dto.InventoryResponse;
import com.shopping.order.dto.OrderLineItemsResponse;
import com.shopping.order.dto.OrderRequest;
import com.shopping.order.model.Order;
import com.shopping.order.model.OrderLineItems;
import com.shopping.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;



    public void placeOrder(OrderRequest orderRequest) {
        Order order= new Order();
        order.setOrderNumber(UUID.randomUUID());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsResponse().stream().map((this::mapToDTO)).toList();
        order.setOrderLineItems(orderLineItems);
        List <String> skuCodes= order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();
        InventoryResponse [] inventoryResponses = webClientBuilder.build().get().uri("http://InventoryService/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build()).retrieve()
                .bodyToMono(InventoryResponse[].class).block();

       boolean allProductInStock= Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);
        if (allProductInStock)
            orderRepository.save(order);
        else
            throw new IllegalArgumentException("Product is not in stock please try again later");
    }
    private OrderLineItems mapToDTO(OrderLineItemsResponse orderLineItemsResponse) {
        OrderLineItems orderLineItems= new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsResponse.getPrice());
        orderLineItems.setQuantity(orderLineItemsResponse.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsResponse.getSkuCode());
        return orderLineItems;
    }
}
