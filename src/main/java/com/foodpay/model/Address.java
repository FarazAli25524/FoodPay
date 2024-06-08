package com.foodpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addr_id;

    private String addr_strt_1;

    private String addr_strt_2;

    private String addr_city;

    private String addr_state;

    private String addr_zip;

    private String addr_country;

}
