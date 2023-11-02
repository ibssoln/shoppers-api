package com.ibssoln.shoppers.controller;

import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.service.BatchService;
import com.ibssoln.shoppers.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getAllItems() throws ShoppersException {
        List<Item> items = new ArrayList<>();
        try {
            items = itemService.getAllItems();
        }catch (Exception e){
            throw new ShoppersException("Error occurred while updating inventory.");
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


}
