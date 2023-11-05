package com.ibssoln.shoppers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Date;

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

    @Column(name="image")
    private String image;

    @Column(name="open_until")
    private OffsetDateTime openUntil;

    @Column(name="registered_at")
    private OffsetDateTime registeredAt;

    @Column(name="address")
    private String address;

}
