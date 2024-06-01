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
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    private long id;

    @ManyToOne
    @Column(name = "order_item_food")
    private Food food;

    @Column(name = "order_item_quantity")
    private int quantity;

    @Column(name = "order_item_totalPrice")
    private long totalPrice;

    @Column(name = "order_item_ingredients")
    private List<String> ingredients;

}
