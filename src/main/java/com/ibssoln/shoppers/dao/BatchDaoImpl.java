package com.ibssoln.shoppers.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.dto.InventoryOrderReceipt;
import com.ibssoln.shoppers.entity.Inventory;
import com.ibssoln.shoppers.service.BatchService;
import com.ibssoln.shoppers.service.FTPService;
import com.ibssoln.shoppers.service.InventoryService;
import com.ibssoln.shoppers.dto.FileInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BatchDaoImpl {

    private static final Logger log = LoggerFactory.getLogger(BatchService.class);
    private static final String ORDER_RECEIPTS_TARGET_URL = "http://localhost:8080/info/order/receipts";
    private static final String SHOP_CODES_TARGET_URL = "http://localhost:8080/info/shop/codes";

    @Autowired
    private InventoryDaoImpl inventoryDaoImpl;

    @Autowired
    private InventoryService inventoryService;
    
    @Autowired
    private FTPService ftpService;

    public String batchSend(String itemId){
        String status = "OK";
        FileInfo fileInfo = null;
        try{
            List<Inventory> inventoriesUnder10 = inventoryDaoImpl.getInventoryByItemUnderLimit(itemId, 10L);
            List<String > storeIds = inventoriesUnder10.stream().map(i -> i.getInventoryPK().getStoreId()).collect(Collectors.toList());
            Map<String, String> shopOrderCodes = shopOrderCodes(storeIds);
            log.info("Item id {} is running low in {} number of stores.", itemId, inventoriesUnder10.size());
            if(!CollectionUtils.isEmpty(inventoriesUnder10)){
                log.info("Sending a flat file for item id {}, # of stores running low: {}", itemId, inventoriesUnder10.size());
                fileInfo = ftpService.sendInventoryOrderFTP(itemId, inventoriesUnder10, shopOrderCodes);
            }
        } catch (Exception e) {
            log.error("BatchSend - ftp a flat file failed.", e);
            status = "FAILED_FILE_FTP";
        }
        String result = inventoryService.sendInventoryOrder(itemId, fileInfo);
        if(StringUtils.isEmpty(result) || result.equals("INVALID_ITEM") || result.equals("WS_FAILED")){
            log.info("Failed to submit the request to Web Service for id {}, result {}", itemId, result);
            status = "WS_FAILED";
        }else if (result.equals("NO_TARGET_TO_SEND")){
            log.info("No target to send for id {}", itemId);
            status = "NO_TARGET_TO_SEND";
        }else{
            log.info("Successfully submitted the request to Web Service for id {}", itemId);
        }
        return status;
    }

    private Map<String, List<InventoryOrderReceipt>> getBulkReceipts(List<Inventory> inventories) {
        Map<String, List<InventoryOrderReceipt>> responseMap = null;
        try{
            log.error("BulkReceipts requests started for {} number of inventories.", inventories.size());
            List<String> itemIds = inventories.stream().map(i -> i.getItem().getId()).collect(Collectors.toList());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(itemIds);
            HttpRequest httpRequest = HttpRequest.newBuilder().header("Content-Type", "application/json")
                    .uri(new URI(ORDER_RECEIPTS_TARGET_URL)).POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            responseMap = objectMapper.readValue(response.body() , new TypeReference<Map<String, List<InventoryOrderReceipt>>>(){});
            log.info("bulkReceipts = "+responseMap);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            log.error("BulkReceipts requests failed.", e);
        }
        return responseMap;
    }

    private Map<String, String> shopOrderCodes(List<String> shopIds) throws ShoppersException {
        Map<String, String> shopCodeMap = new HashMap<>();
        try{
            if(!CollectionUtils.isEmpty(shopIds)){
                int sizeShopIds = shopIds.size();
                int sizeBatch = 200;
                int iteration = sizeShopIds / sizeBatch;
                int remainder = sizeShopIds % sizeBatch;
                log.info("shopCode - # total shopIds: {}, # total iteration: {}, # remainder: {}", sizeShopIds, iteration, remainder);
                for(int i = 0; i <= iteration; i++){
                    int start = (i * sizeBatch);
                    int end;
                    if(i == iteration){
                        end = start + remainder;
                    }else{
                        end = start + sizeBatch;
                    }
                    String rangeIndex = "start index "+ start + ", end index: "+(end - 1);
                    log.info("shopCode - iteration #: {} - [range: {}]", i, rangeIndex);
                    getShopCode(shopIds, start, end, shopCodeMap);
                }
            }
        } catch (Exception e) {
            log.error("Shop codes requests failed.", e);
            throw new ShoppersException("Shop codes requests failed.", e);
        }
        return shopCodeMap;
    }

    private void getShopCode(List<String> shopIds, int start, int end, Map<String, String> shopCodeMap) throws URISyntaxException, IOException, InterruptedException {
        try{
            log.error("Requesting approval codes for {} number of shops.", shopIds.size());
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                    shopIds.subList(start, end).stream().filter(rec -> !StringUtils.isBlank(rec)).collect(Collectors.toList()));
            HttpRequest httpRequest = HttpRequest.newBuilder().header("Content-Type", "application/json")
                    .uri(new URI(SHOP_CODES_TARGET_URL)).POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            Map<String, String> resultMap = objectMapper.readValue(response.body() , new TypeReference<Map<String, String>>(){});
            log.info("shops codes = "+resultMap);
            shopCodeMap.putAll(resultMap);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
