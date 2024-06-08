package com.foodpay.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long food_id;

    private String food_name;

    private String food_desc;

    private long food_pri;

    @ManyToOne
    private Category food_cat;

    @ElementCollection
    @Column(length = 1000)
    private List<String> food_img;

    private boolean food_ava;

    @ManyToOne
    private Restaurant food_rest;

    private boolean food_is_vege;

    private boolean food_is_seas;

    @ManyToMany
    private List<IngredientItem> food_ingr = new ArrayList<>();

    private Date food_crea_date;

}
