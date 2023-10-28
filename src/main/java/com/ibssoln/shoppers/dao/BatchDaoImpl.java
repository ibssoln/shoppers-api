package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.entity.Inventory;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.service.BatchService;
import com.ibssoln.shoppers.service.FTPService;
import com.ibssoln.shoppers.service.InventoryService;
import com.ibssoln.shoppers.view.FileInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class BatchDaoImpl {

    private static final Logger log = LoggerFactory.getLogger(BatchService.class);
    @Autowired
    private InventoryDaoImpl inventoryDaoImpl;

    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private FTPService ftpService;

    public String batchSend(String itemId){
        String status = "OK";
        FileInfo fileInfo = null;
        List<Inventory> inventoriesUnder10 = inventoryDaoImpl.getInventoryByItemUnderLimit(itemId, 10L);
        try{
            log.info("Item id {} is running low in {} number of stores.", itemId, inventoriesUnder10.size());
            if(!CollectionUtils.isEmpty(inventoriesUnder10)){
                log.info("Sending a flat file for item id {}, # of stores running low: {}", itemId, inventoriesUnder10.size());
                fileInfo = ftpService.sendFTP(itemId, inventoriesUnder10);
            }
        } catch (Exception e) {
            log.error("BatchSend - ftp a flat file failed.", e);
            status = "FAILED_FILE_FTP";
        }
        String result = inventoryService.sendInventoryOrder(itemId, fileInfo);
        if(StringUtils.isEmpty(result) || result.equals("INVALID_ITEM") || result.equals("WS_FAILED")){
            log.info("Failed to submit the request to Web Service for id {}", itemId);
            status = "WS_FAILED";
        }else if (result.equals("NO_TARGET_TO_SEND")){
            log.info("No target to send for id {}", itemId);
            status = "NO_TARGET_TO_SEND";
        }else{
            log.info("Successfully submitted the request to Web Service for id {}", itemId);
        }
        return status;
    }
}
