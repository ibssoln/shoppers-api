package com.ibssoln.shoppers.controller;

import com.ibssoln.shoppers.dao.InventoryDaoImpl;
import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.dto.InventoryOrderReceipt;
import com.ibssoln.shoppers.entity.Inventory;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.repo.ItemRepository;
import com.ibssoln.shoppers.service.BatchService;
import com.ibssoln.shoppers.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private static final Logger log = LoggerFactory.getLogger(BatchController.class);
    @Autowired
    private BatchService batchService;

    @Autowired
    private InventoryDaoImpl inventoryDaoImpl;
    
    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/inventory", method = RequestMethod.POST) 
    public ResponseEntity<Void> updateInventory(@RequestParam String type, @RequestParam String vendorName) throws ShoppersException {
        try {
            batchService.updateInventory(type, vendorName);
        }catch (Exception e){
            throw new ShoppersException("Error occurred while updating inventory.");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/receipts", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, List<InventoryOrderReceipt>>> itemOrderReceipts(@RequestBody List<String> itemIds) throws ShoppersException {
        Map<String, List<InventoryOrderReceipt>> response;
        try {
            response = bookingService.getItemOrderReceipts(itemIds);
        }catch (Exception e){
            throw new ShoppersException("Error occurred while getting receipts.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
