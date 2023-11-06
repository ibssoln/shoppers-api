package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.dao.InventoryDaoImpl;
import com.ibssoln.shoppers.dao.ItemDaoImpl;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.entity.Vendor;
import com.ibssoln.shoppers.repo.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(InfoService.class);

    @Autowired
    private ItemDaoImpl itemDaoImpl;

    public List<Item> getAllItems(){
        return (List<Item>) itemDaoImpl.getAll();
    }

    public List<Item> getSpecialItems(){
        return (List<Item>) itemDaoImpl.getSpecialItems();
    }



}
