package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.dto.StoreDTO;
import com.ibssoln.shoppers.entity.Store;
import com.ibssoln.shoppers.repo.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreDaoImpl {

    private static final Logger log = LoggerFactory.getLogger(StoreDaoImpl.class);

    @Autowired
    private StoreRepository storeRepository;

    public List<StoreDTO> getAll(){
        List<StoreDTO> stores = new ArrayList<>();
        storeRepository.findAll().forEach(store -> {
            stores.add(StoreDTO.builder().id(store.getId()).name(store.getName())
                    .image(store.getImage()).openUntil(store.getOpenUntil().toString())
                    .registeredAt(store.getRegisteredAt().toString()).address(store.getAddress())
                    .build());
        });
        return stores;
    }


}
