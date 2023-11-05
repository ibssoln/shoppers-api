package com.ibssoln.shoppers.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StoreDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String image;

    private String openUntil;

    private String registeredAt;

    private String address;

}
