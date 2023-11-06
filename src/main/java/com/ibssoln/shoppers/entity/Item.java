package com.ibssoln.shoppers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="ITEM")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="item_id")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private Double price;

    @ManyToOne
    @JoinColumn(name="vendor_id")
    private Vendor vendor;

    @Column(name = "special_deal")
    private String specialDeal;

    @Column(name = "image")
    private String image;

    @Column(name = "weight")
    private String weight;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

}
