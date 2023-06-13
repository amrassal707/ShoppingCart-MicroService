package com.ProgrammingOrderService.OrderService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class orderRequest {

    private List<orderLineItemsDTO> orderLineItemsDTOS;

}
