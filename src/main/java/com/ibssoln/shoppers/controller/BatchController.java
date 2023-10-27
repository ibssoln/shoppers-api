package com.ibssoln.shoppers.controller;

import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.repo.ItemRepository;
import com.ibssoln.shoppers.service.BatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private static final Logger log = LoggerFactory.getLogger(BatchController.class);
    @Autowired
    private BatchService batchService;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/inventory", method = RequestMethod.POST) //produces = {"application/json"}, consumes = {"application/json"},
    public ResponseEntity<Void> updateInventory(@RequestParam String type, @RequestParam String vendorName){
        batchService.updateInventory(type, vendorName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> test(@RequestParam String type){
        List<Item> items = itemRepository.getItemsByVendorName(type);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
