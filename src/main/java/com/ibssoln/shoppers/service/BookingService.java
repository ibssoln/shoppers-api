package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.dao.InventoryDaoImpl;
import com.ibssoln.shoppers.dto.InventoryOrderReceipt;
import com.ibssoln.shoppers.entity.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {
    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private InventoryDaoImpl inventoryDaoImpl;

    public Map<String, List<InventoryOrderReceipt>> getItemOrderReceipts(List<String> itemIds){
        Map<String, List<InventoryOrderReceipt>> receiptMap = new HashMap<>();
        itemIds.stream().forEach(itemId -> {
            List<Inventory> inventories = inventoryDaoImpl.getInventoryByItemUnderLimit(itemId, 10L);
            log.info("Item {} has {} number of shops to order.", itemId, inventories.size());
            List<InventoryOrderReceipt> receipts = new ArrayList<>();
            if(!CollectionUtils.isEmpty(inventories)){
                inventories.stream().forEach(inv -> {
                    receipts.add(InventoryOrderReceipt.builder().receiptNo(inv.getInventoryPK().getItemId()+"-"+inv.getInventoryPK().getStoreId())
                            .itemId(inv.getInventoryPK().getItemId()).storeId(inv.getInventoryPK().getStoreId()).orderedAt(LocalDateTime.now()).count(100L)
                            .delieveryDue(LocalDate.now().plusDays(14)).build());
                });
            }
            receiptMap.put(itemId, receipts);
        });
        return receiptMap;
    }

}
