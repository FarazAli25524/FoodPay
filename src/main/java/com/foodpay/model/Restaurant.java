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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "res_id")
    private long id;

    @OneToOne
    @Column(name = "res_owner")
    private User owner;

    @Column(name = "res_name")
    private String name;

    @Column(name = "res_desc")
    private String description;

    @Column(name = "res_cui_type")
    private String cuisineType;

    @OneToOne
    @Column(name = "res_addr")
    private Address address;

    @Embedded
    @Column(name = "res_cont")
    private Contact contact;

    @Column(name = "res_openhrs")
    private String openhrs;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "res_ordr")
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    @Column(name = "res_img", length = 1000)
    private List<String> image;

    @Column(name = "res_reg")
    private LocalDateTime registrationDate;

    @Column(name = "res_open")
    private boolean resOpen;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @Column(name = "res_food")
    private  List<Food> food = new ArrayList<>();


}
