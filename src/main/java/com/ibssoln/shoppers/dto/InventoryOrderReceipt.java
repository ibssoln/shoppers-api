package com.ibssoln.shoppers.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class InventoryOrderReceipt implements Serializable {
    private static final long serialVersionUID = 1L;
    private String receiptNo;
    private String storeId;
    private String itemId;
    private Long count;
    private LocalDateTime orderedAt;

    private LocalDate delieveryDue;

}
