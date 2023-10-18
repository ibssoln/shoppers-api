package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.entity.Vendor;
import com.ibssoln.shoppers.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private VendorService vendorService;

    public Item findOrCreateItem(String id, String name){
        return itemRepository.findById(id).orElse(itemRepository.save(Item.builder().id(id).name(name).build()));
    }

    public Item findItem(String id){
        return itemRepository.findById(id).orElseThrow(() ->  new RuntimeException("The item does not exist."));
    }

    public Item updateItemVendor(String itemId, String vendorId){
        Vendor vendor = vendorService.findVendor(vendorId);
        Item item = findItem(itemId);
        return itemRepository.save(item);
    }

    public Iterable<Item> getAll(){
        return itemRepository.findAll();
    }

    public long getCountAllItems(){
        return itemRepository.count();
    }

}
