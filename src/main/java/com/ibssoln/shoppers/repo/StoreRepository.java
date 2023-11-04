package com.ibssoln.shoppers.repo;

import com.ibssoln.shoppers.entity.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, String> {



}
