package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.entity.Inventory;
import com.ibssoln.shoppers.repo.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InventoryDaoImpl {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getInventoryByItemUnderLimit(String itemId, Long stockLimit){
        return inventoryRepository.getInventoryByItemUnderLimit(itemId, stockLimit);
    }

    public List<Inventory> getInventoryByItem(String itemId){
        return inventoryRepository.getInventoryByItem(itemId);
    }

}
