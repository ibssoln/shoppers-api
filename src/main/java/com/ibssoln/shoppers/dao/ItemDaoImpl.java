package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.entity.Vendor;
import com.ibssoln.shoppers.repo.ItemRepository;
import com.ibssoln.shoppers.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemDaoImpl {

    @Autowired
    private VendorDaoImpl vendorDaoImpl;

    @Autowired
    private ItemRepository itemRepository;

    public Item findOrCreateItem(String id, String name){
        return itemRepository.findById(id).orElse(itemRepository.save(Item.builder().id(id).name(name).build()));
    }

    public Item findItem(String id){
        return itemRepository.findById(id).orElseThrow(() ->  new RuntimeException("The item does not exist."));
    }

    public Item findById(String id){
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItemVendor(String itemId, String vendorId){
        Vendor vendor = vendorDaoImpl.findVendor(vendorId);
        Item item = findItem(itemId);
        return itemRepository.save(item);
    }

    public Iterable<Item> getAll(){
        return itemRepository.findAll();
    }

    public long getCountAllItems(){
        return itemRepository.count();
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
