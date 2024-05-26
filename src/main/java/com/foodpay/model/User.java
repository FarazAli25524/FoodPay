package com.foodpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodpay.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_fullname")
    private String fullname;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_role")
    private USER_ROLE role;

    @JsonIgnore
    @OneToMany
    @Column(name = "user_order")
    private List<Order> orders = new ArrayList<>();


    @ElementCollection
    @Column(name = "user_favorite")
    private List<RestaurantDto> favorites = new ArrayList<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @Column(name = "user_address")
    private List<Address> addresses = new ArrayList<>();


}
