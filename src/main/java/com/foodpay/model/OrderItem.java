package com.foodpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderItem  ")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ordr_id;

    @ManyToOne
    private Food ordr_food;

    private int ordr_quan;

    private long ordr_tot_pri;

    private List<String> ordr_ingr;

}
