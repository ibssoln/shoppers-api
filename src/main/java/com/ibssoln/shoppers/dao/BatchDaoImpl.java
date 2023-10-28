package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.entity.Inventory;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.service.BatchService;
import com.ibssoln.shoppers.service.FTPService;
import com.ibssoln.shoppers.view.FileInfo;
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
    private FTPService ftpService;

    public String batchSend(String itemId){
        String status = "OK";
        FileInfo fileInfo = null;
        try{
            List<Inventory> inventoriesUnder10 = inventoryDaoImpl.getInventoryByItemUnderLimit(itemId, 10L);
            log.info("Item id {} is running low in {} number of stores.", itemId, inventoriesUnder10.size());
            if(!CollectionUtils.isEmpty(inventoriesUnder10)){
                log.info("Sending a flat file for item id {}, # of stores running low: {}", itemId, inventoriesUnder10.size());
                fileInfo = ftpService.sendFTP(itemId, inventoriesUnder10);
            }

        } catch (Exception e) {
            log.error("BatchSend - ftp a flat file failed.", e);
            status = "FAILED_FILE_FTP";
        }
        //TODO

        return status;
    }



}
