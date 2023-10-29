package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.dao.ItemDaoImpl;
import com.ibssoln.shoppers.entity.Item;
import com.ibssoln.shoppers.dto.FileInfo;
import com.ibssoln.shoppers.soap.outbound.inventory.client.InventoryProviderWSClient;
import com.ibssoln.shoppers.soap.outbound.inventory.dto.ProvideInventoryCANResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.ws.client.core.WebServiceMessageCallback;
import java.util.Objects;

@Service
public class InventoryService {
    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
    @Autowired
    private ItemDaoImpl itemDaoImpl;

    @Autowired
    private InventoryProviderWSClient inventoryProviderWSClient;

    public String sendInventoryOrder(String itemId, FileInfo fileInfo){
        String result = "INVALID_ITEM";
        try{
           Item item = itemDaoImpl.findById(itemId);
           if(Objects.nonNull(item)){
               String vendorLocation = item.getVendor().getFactoryLoc();
               if(vendorLocation.equalsIgnoreCase("CAN")){
                   log.info("Sending web service to CAN for id {}", itemId);
                   //------- callback (security) -----
//                   String samlString = ""; //tokenProvider.getWSSecurityToken();
//                   WebServiceMessageCallback callBack = new WebServiceMessageCallbackImpl(samlString);
//                   ProvideInventoryCANResponse response = inventoryProviderWSClient.callInventoryProvider(itemId, fileInfo.getFileName(), callBack);
                   //------- callback (security) -----
                   ProvideInventoryCANResponse response = inventoryProviderWSClient.callInventoryProvider(itemId, fileInfo.getFileName());
                   result = response.getReturn();
               }else if (vendorLocation.equalsIgnoreCase("USA")){
                   log.info("Sending web service to USA for id {}", itemId);
                   //TODO (MQ)
                   result = "SENT_USA";
               }else{
                   result = "NO_TARGET_TO_SEND";
               }
           }
//        } catch (WebServiceClientException e){
//            result = "WS_FAILED";
//            log.error("WebServiceClientException occurred while sending inventory order for id {}", itemId, e);
        } catch (Exception e){
            result = "WS_FAILED";
            log.error("Exception occurred while sending inventory order for id {}", itemId, e);
        }
        return result;
    }

//    private String sendFillItemInventoryByFactoryCANRequest(FillItemInventoryByFactoryCANRequest request){
//        //call soap client
//        return "SENT_CAN";
//    }

}
