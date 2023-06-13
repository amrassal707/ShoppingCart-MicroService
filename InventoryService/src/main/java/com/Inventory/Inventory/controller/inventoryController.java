package com.Inventory.Inventory.controller;

import com.Inventory.Inventory.DTO.InventoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.Inventory.Inventory.service.inventoryService;

import java.util.*;

@RestController
@RequestMapping("/api/inventory")
public class inventoryController {
    private final inventoryService inventoryService;

    public inventoryController(inventoryService inventoryService) {
        this.inventoryService= inventoryService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }


}
