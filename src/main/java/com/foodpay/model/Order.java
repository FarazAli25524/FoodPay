package com.foodpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name  = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ordr_id;

    @ManyToOne
    private User ordr_cust;

    @JsonIgnore
    @ManyToOne
    private Restaurant ordr_rest;

    private long ordr_tot_amount;

    private String ordr_stat;

    private Date ordr_crea_date;

    @JsonIgnore
    @ManyToOne
    private Address ordr_del_addr;

    @OneToMany
    private List<OrderItem> ordr_item;

//    private Payment ordr_paym;

    private int ordr_tot_item;

    private int ordr_tot_pri;

}
