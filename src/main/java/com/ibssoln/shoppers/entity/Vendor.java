package com.ibssoln.shoppers.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name="VENDOR")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vendor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="vendor_id")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="factory_loc")
    private String factoryLoc;

}
