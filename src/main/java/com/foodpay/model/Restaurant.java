package com.foodpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rest_id;

    @OneToOne
    private User rest_owner;

    private String rest_name;

    private String rest_desc;

    private String rest_cuis_type;

    @OneToOne
    private Address rest_addr;

    @OneToOne
    private Contact rest_cont;

    private String rest_open_hrs;

    @OneToMany(mappedBy = "ordr_rest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> rest_ordr = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> rest_img;

    private LocalDateTime rest_reg_date;

    private boolean rest_open;

    @JsonIgnore
    @OneToMany(mappedBy = "food_rest", cascade = CascadeType.ALL)
    private  List<Food> rest_food = new ArrayList<>();


}
