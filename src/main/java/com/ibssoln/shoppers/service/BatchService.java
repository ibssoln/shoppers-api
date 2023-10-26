package com.ibssoln.shoppers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    private static final Logger log = LoggerFactory.getLogger(BatchService.class);

    @Async("globalBatchExecutor")
    public void updateInventory(){
        log.error("#Shoppers - fill inventory");
    }

}
