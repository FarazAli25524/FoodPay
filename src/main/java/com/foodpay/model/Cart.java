package com.foodpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cart_id;

    @OneToOne
    private User cart_cus_id;

    private long cart_total;

    @OneToMany(mappedBy = "cart_itm_cart_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cart_item = new ArrayList<>();
}
