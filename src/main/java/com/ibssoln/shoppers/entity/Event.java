package com.ibssoln.shoppers.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name="EVENT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="event_id")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="start_date")
    private String start_date;

    @Column(name="end_date")
    private String end_date;

}
