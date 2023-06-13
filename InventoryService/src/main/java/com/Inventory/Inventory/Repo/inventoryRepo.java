package com.Inventory.Inventory.Repo;

import com.Inventory.Inventory.Model.inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface inventoryRepo extends JpaRepository<inventory,Long> {
    List<inventory> findBySkuCodeIn(List<String> skuCode);
}
