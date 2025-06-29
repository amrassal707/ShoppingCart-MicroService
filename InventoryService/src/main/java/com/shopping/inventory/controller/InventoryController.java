package com.shopping.inventory.controller;

import com.shopping.inventory.dto.InventoryResponse;
import com.shopping.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {
    private final InventoryService inventoryService;


    @GetMapping
    public ResponseEntity<Object> isInStock(@RequestParam List<String> skuCode) {
        log.info("skuCode {}", skuCode);
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.isInStock(skuCode));
    }


}
