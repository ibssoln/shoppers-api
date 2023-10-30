package com.ibssoln.shoppers.controller;

import com.ibssoln.shoppers.dao.InventoryDaoImpl;
import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.service.BatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private static final Logger log = LoggerFactory.getLogger(BatchController.class);
    @Autowired
    private BatchService batchService;

    @Autowired
    private InventoryDaoImpl inventoryDaoImpl;

    @RequestMapping(value = "/inventory", method = RequestMethod.POST) 
    public ResponseEntity<Void> updateInventory(@RequestParam String type, @RequestParam String vendorName) throws ShoppersException {
        try {
            batchService.updateInventory(type, vendorName);
        }catch (Exception e){
            throw new ShoppersException("Error occurred while updating inventory.");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
