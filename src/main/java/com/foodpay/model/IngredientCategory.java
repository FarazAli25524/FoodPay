package com.foodpay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "ingredientCategory")
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ing_cat_id;

    private String ing_cat_name;

    @JsonIgnore
    @ManyToOne
    private Restaurant ing_cat_res;

    @OneToMany(mappedBy = "ing_item_cat", cascade = CascadeType.ALL)
    private List<IngredientItem> ing_cat_ing_item = new ArrayList<>();
}
