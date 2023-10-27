package com.ibssoln.shoppers.repo;

import com.ibssoln.shoppers.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String> {

    @Query(value = "SELECT item FROM Item item where item.vendor.name = :vm")
    List<Item> getItemsByVendorName(@Param("vm") String vendorName);



}
