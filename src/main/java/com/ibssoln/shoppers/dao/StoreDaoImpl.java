package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.entity.Store;
import com.ibssoln.shoppers.repo.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreDaoImpl {

    private static final Logger log = LoggerFactory.getLogger(StoreDaoImpl.class);

    @Autowired
    private StoreRepository storeRepository;

    public Iterable<Store> getAll(){
        return storeRepository.findAll();
    }


}
