package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.dao.BatchDaoImpl;
import com.ibssoln.shoppers.dao.EmailDaoImpl;
import com.ibssoln.shoppers.dao.ItemDaoImpl;
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
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
@Service
public class BatchService {

    private static final Logger log = LoggerFactory.getLogger(BatchService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BatchDaoImpl batchDaoImpl;

    @Autowired
    private ItemDaoImpl itemDaoImpl;

    @Autowired
    private EmailDaoImpl emailDaoImpl;

    @Async("globalBatchExecutor")
    public CompletableFuture<String> updateInventory(String type, String vendorName){
        try{
            LocalDateTime dateTime = LocalDateTime.now();
            log.error("#Shoppers - fill inventory - type: {}, dateTime: {}", type, dateTime);
            List<Item> itemsList = new ArrayList<>();
            switch (type){
                case "vendorSpecific":
                    itemsList = itemRepository.getItemsByVendorName(vendorName);
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
        ExecutorService executor = Executors.newFixedThreadPool(100);
        try{
            List<CompletableFuture<Map<String, String>>> tasks = new ArrayList<>();
            for (Item item: itemsList){
                tasks.add(CompletableFuture.supplyAsync(() -> processRequest(item.getId()), executor)
                        .exceptionally(e -> Map.of(item.getId(), "EXCEPTION: " + e)));
            }
            CompletableFuture<Void> allTasks = CompletableFuture
                    .allOf(tasks.toArray(new CompletableFuture[tasks.size()]));
            allTasks.join();
            CompletableFuture<List<? extends Object>> allTasksResults = allTasks
                    .thenApply(t -> tasks.stream().map(CompletableFuture::join).collect(Collectors.toList()));
            try{
                List<? extends Object> taskResults = allTasksResults.get();
                log.info("Batch job finished. The result is as follows [(id = result)]: {}", taskResults);
            } catch (InterruptedException e) {
                log.error("InterruptedException occurred whiile extracting the batch results: {}", e.getMessage());
                Thread.currentThread().interrupt();
                throw new ShoppersException("Thread interrupted exception while submitting the job.");
            } catch (ExecutionException e) {
                log.error("ExecutionException occurred while extracting the batch results: {}", e.getMessage());
                throw new ShoppersException("ExecutionException occurred while extracting the batch.");
            }
        } finally {
            executor.shutdownNow();
            log.info("Inventory batch executor shut down.");
        }
    }

    /** This method is asynchrously processed **/
    private Map<String, String> processRequest(String id){
        Map<String, String> taskResult = new HashMap<>();
        boolean failed = false;
        String status = batchDaoImpl.batchSend(id);
        if (status.equalsIgnoreCase("OK")){
            log.info("Item was filled successfully for item id: {}", id);
            Item item = null;
            try{
                item = itemDaoImpl.findById(id);
            } catch (Exception e){
                log.error("Error retrieving the item id: {}", id);
            }
            if (Objects.nonNull(item)){
                log.info("Successfully filled the item id {}, status {}", id, status);
                itemDaoImpl.updateItemStatus(id, "Filled");
                taskResult.put(item.getId(), "SUCCESS");
            }
        }else if(status.equalsIgnoreCase("NoStocksToFill")){
            log.info("There is no stocks to fill for item id {}", id);
            itemDaoImpl.updateItemStatus(id, "NoStock");
            taskResult.put(id, "SUCCESS");
        }else{
            log.error("Inventory fill failed for the item id {}, status {}", id, status);
            failed = itemDaoImpl.updateItemStatus(id, "FillFailed");
            taskResult.put(id, "FAILURE");
        }
        if (failed){
            sendEmailForFailedItem(id);
        }
        return taskResult;
    }

    private void sendEmailForFailedItem(String id){
        try{
            log.info("Sending a notification email for the failed inventory fill batch for id {}", id);
            Item item = itemDaoImpl.findById(id);
            emailDaoImpl.sendEmailNotification(item);
        } catch (ShoppersException e){
            log.error("System error occurred while sending an email notification for item id {}, error {}", id, e);
        } catch (Exception e){
            log.error("Error occurred while sending an email notification for item id {}, error {}", id, e);
        }
    }

}
