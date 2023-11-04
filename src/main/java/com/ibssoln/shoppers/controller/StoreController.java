package com.ibssoln.shoppers.controller;

import com.ibssoln.shoppers.dao.InventoryDaoImpl;
import com.ibssoln.shoppers.dao.StoreDaoImpl;
import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.entity.Store;
import com.ibssoln.shoppers.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    private static final Logger log = LoggerFactory.getLogger(StoreController.class);
    @Autowired
    private StoreService storeService;
    @RequestMapping(value = "/stores", method = RequestMethod.GET)
    public ResponseEntity<List<Store>> getStores() throws ShoppersException {
        List<Store> stores = new ArrayList<>();
        try {
            stores = storeService.getAllStores();
        }catch (Exception e){
            throw new ShoppersException("Error occurred while fetching stores.");
        }
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }


}
