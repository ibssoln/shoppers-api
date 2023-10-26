package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.repo.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BatchService {
    private static final Logger log = LoggerFactory.getLogger(BatchService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Async("globalBatchExecutor")
    public CompletableFuture<String> updateInventory(String type){
        try{
            LocalDateTime dateTime = LocalDateTime.now();
            log.error("#Shoppers - fill inventory - type: {}, dateTime: {}", type, dateTime);
            List<Item> itemsList = new ArrayList<>();
            switch (type){
                case "grocery":
                    itemRepository.findAll().forEach(itemsList::add);
                    break;
                default:
                    throw new ShoppersException("Invalid type of inventory was requested.");
            }
            if(CollectionUtils.isEmpty(itemsList)){
                log.info("No items to process.");
            }else{
                log.info("The num of items to process: {}", itemsList.size());
                processItems(itemsList);
            }
        }catch(Exception e){
            log.error("Exception occurred while filling the inventory: {}", e.getMessage());
        }
        return CompletableFuture.completedFuture("COMPLETED");
    }

    private void processItems(List<Item> itemsList) throws ShoppersException{
        //TODO

    }

}
