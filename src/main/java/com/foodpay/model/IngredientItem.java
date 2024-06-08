package com.foodpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredientItem")
public class IngredientItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ing_item_id;

    private String ing_item_name;

    @ManyToOne
    private IngredientCategory ing_item_cat;

    @JsonIgnore
    @ManyToOne
    private Restaurant ing_item_res;

    private boolean ing_in_stk = false;
}
