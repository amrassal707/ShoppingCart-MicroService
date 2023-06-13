package com.Inventory.Inventory.service;

import com.Inventory.Inventory.DTO.InventoryResponse;

import com.Inventory.Inventory.Model.inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Inventory.Inventory.Repo.inventoryRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class inventoryService {

    private inventoryRepo inventoryRepo;
    @Autowired
    public inventoryService(inventoryRepo inventoryRepo) {
        this.inventoryRepo= inventoryRepo;
    }


    public List<InventoryResponse> isInStock(List<String> skuCode) {
      return inventoryRepo.findBySkuCodeIn(skuCode).stream().map(this::mapToDTO).toList();
      /*
        List <InventoryResponse> inventoryResponseList = inventoryRepo.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder().skuCode(inventory.getSkuCode()).isInStock(inventory.getQuantity()>0).build()).toList();
        return inventoryResponseList;

       */
    }

    private InventoryResponse mapToDTO(inventory inventory) {

        return new InventoryResponse(inventory.getSkuCode(), inventory.getQuantity()>0);

    }

}
