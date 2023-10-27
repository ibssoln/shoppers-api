package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemDaoImpl {

    @Autowired
    private ItemRepository itemRepository;

    public Item findById(String id){
       return itemRepository.findById(id).orElse(null);
    }

    public boolean updateItemStatus(String id, String status){
        boolean updated = false;
        try {
            //Todo
            updated = true;
        } catch (Exception e){
            return updated;
        }
        return updated;
    }

}
