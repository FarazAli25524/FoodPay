package com.foodpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cart_itm_id;

    @JsonIgnore
    @ManyToOne
    private Cart cart_itm_cart_id;

    @ManyToOne
    private Food cart_itm_food_id;

    private int cart_itm_qty;

    private List<String> cart_itm_ingr;

    private long cart_itm_tot_pri;
}
