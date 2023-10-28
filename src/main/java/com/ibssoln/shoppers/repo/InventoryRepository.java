package com.ibssoln.shoppers.repo;

import com.ibssoln.shoppers.entity.Inventory;
import com.ibssoln.shoppers.entity.InventoryPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, InventoryPK> {

    @Query(value = "SELECT inventory FROM Inventory inventory where inventory.item.id = :itemId and inventory.stock < :stockLimit")
    List<Inventory> getInventoryByItemUnderLimit(@Param("itemId") String itemId, @Param("stockLimit") Long stockLimit);



}
