package com.ibssoln.shoppers.repo;

import com.ibssoln.shoppers.entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {

    @Query(value = "SELECT item FROM Item item where item.vendor.name = :vm")
    List<Item> getItemsByVendorName(@Param("vm") String vendorName);

    @Query(value = "SELECT item FROM Item item where item.specialDeal = 'Y'")
    List<Item> getSpecialItems();

    @Query(value = "SELECT item FROM Item item join Inventory inventory on item.id = inventory.inventoryPK.itemId where inventory.inventoryPK.storeId = :storeId")
    List<Item> getItemsByStoreId(@Param("storeId") String storeId);

}
