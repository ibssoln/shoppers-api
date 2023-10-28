package com.ibssoln.shoppers.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name="INVENTORY")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private InventoryPK inventoryPK;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name="store_id", insertable=false, updatable=false)
    private Store store;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name="item_id", insertable=false, updatable=false)
    private Item item;

    @Column(name="stock")
    private Long stock;

}
