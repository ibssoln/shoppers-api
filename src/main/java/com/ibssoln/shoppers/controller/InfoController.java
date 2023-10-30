package com.ibssoln.shoppers.controller;

import com.ibssoln.shoppers.dao.InventoryDaoImpl;
import com.ibssoln.shoppers.dao.ItemDaoImpl;
import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.dto.InventoryOrderReceipt;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/info")
public class InfoController {
    private static final Logger log = LoggerFactory.getLogger(InfoController.class);
    @Autowired
    private ItemDaoImpl itemDaoImpl;

    @Autowired
    private InfoService infoService;

    @Autowired
    private InventoryDaoImpl inventoryDaoImpl;
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> itemsForVendor(@RequestParam String vendorName){
        List<Item> items = itemDaoImpl.getItemsByVendorName(vendorName);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/receipts", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, List<InventoryOrderReceipt>>> itemInventoryOrderReceipts(@RequestBody List<String> itemIds) throws ShoppersException {
        Map<String, List<InventoryOrderReceipt>> response;
        try {
            response = infoService.getItemInventoryOrderReceipts(itemIds);
        }catch (Exception e){
            throw new ShoppersException("Error occurred while getting receipts.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/shop/codes", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> shopOrderApprovalCodes(@RequestBody List<String> shopIds) throws ShoppersException {
        Map<String, String> response;
        try {
            response = infoService.getShopOrderApprovalCodes(shopIds);
        }catch (Exception e){
            throw new ShoppersException("Error occurred while getting shop approval codes.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
