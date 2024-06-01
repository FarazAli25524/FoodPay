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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private long id;

    @JsonIgnore
    @ManyToOne
    @Column(name = "cart_item_cart")
    private Cart cart;

    @ManyToOne
    @Column(name = "cart_item_food")
    private Food food;

    @Column(name = "cart_item_quan")
    private int quantity;

    @Column(name = "cart_item_ingr")
    private List<String> ingredients;

    @Column(name = "cart_item_totalPri")
    private long totalPrice;
}
