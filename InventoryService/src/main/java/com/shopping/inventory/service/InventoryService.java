package com.shopping.inventory.service;

import com.shopping.inventory.dto.InventoryResponse;

import com.shopping.inventory.model.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.shopping.inventory.repository.InventoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    public List<InventoryResponse> isInStock(List<String> skuCode) {
        if (skuCode == null || skuCode.isEmpty()) {
            return List.of();
        }
        List<InventoryResponse> inventoryResponses = inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(this::mapToDTO)
                .toList();
      return inventoryResponses.isEmpty() ? List.of() : inventoryResponses;

    }

    private InventoryResponse mapToDTO(Inventory inventory) {

        return new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity()>0);

    }

}
