package com.ibssoln.shoppers.service;

import com.ibssoln.shoppers.dao.StoreDaoImpl;
import com.ibssoln.shoppers.dto.StoreDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class StoreService {
    private static final Logger log = LoggerFactory.getLogger(StoreService.class);

    @Autowired
    private StoreDaoImpl storeDaoImpl;

    public List<StoreDTO> getAllStores(){
        return (List<StoreDTO>) storeDaoImpl.getAll();
    }



}
