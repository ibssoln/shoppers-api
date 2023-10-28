package com.ibssoln.shoppers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class InventoryPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="store_id")
    private String storeId;

    @Column(name="item_id")
    private String itemId;

}
