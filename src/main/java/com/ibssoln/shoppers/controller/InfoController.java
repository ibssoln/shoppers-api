package com.ibssoln.shoppers.controller;

import com.ibssoln.shoppers.dao.InventoryDaoImpl;
import com.ibssoln.shoppers.dao.ItemDaoImpl;
import com.ibssoln.shoppers.dto.InventoryOrderReceipt;
import com.ibssoln.shoppers.entity.Inventory;
import com.ibssoln.shoppers.entity.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/info")
public class InfoController {
    private static final Logger log = LoggerFactory.getLogger(InfoController.class);
    @Autowired
    private ItemDaoImpl itemDaoImpl;

    @Autowired
    private InventoryDaoImpl inventoryDaoImpl;
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> itemsForVendor(@RequestParam String vendorName){
        List<Item> items = itemDaoImpl.getItemsByVendorName(vendorName);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


}
