package com.ibssoln.shoppers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="CATEGORY")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="category_id")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="image")
    private String image;

}
