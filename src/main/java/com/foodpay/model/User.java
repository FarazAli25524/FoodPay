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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    private String user_full_name;

    private String user_email;

    private String user_pass;

    private USER_ROLE user_role;

    @JsonIgnore
    @OneToMany
    private List<Order> user_ords = new ArrayList<>();

    @ElementCollection
    private List<RestaurantDto> user_favr = new ArrayList<>();

    @JsonIgnore
    @OneToMany( mappedBy = "addr_id", cascade = CascadeType.ALL)
    private List<Address> user_addr = new ArrayList<>();
}
