package com.ibssoln.shoppers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="STORE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="store_id")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

}
