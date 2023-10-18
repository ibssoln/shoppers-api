package com.ibssoln.shoppers.repo;

import com.ibssoln.shoppers.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {



}
