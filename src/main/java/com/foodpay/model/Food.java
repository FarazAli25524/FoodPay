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
@Table(name = "table")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id")
    private long id;

    @Column(name = "food_name")
    private String name;

    @Column(name = "food_desc")
    private String description;

    @Column(name = "food_price")
    private long price;

    @ManyToOne
    @Column(name = "food_cat")
    private Category category;

    @ElementCollection
    @Column(name = "food_img", length = 1000)
    private List<String> image;

    @Column(name = "food_isAvaliable")
    private boolean available;

    @ManyToOne
    @Column(name = "food_rest")
    private Restaurant restaurant;

    @Column(name = "food_isVegetarian")
    private boolean isVegetarian;

    @Column(name = "food_isSeasonal")
    private boolean isSeasonal;

    @ManyToMany
    @Column(name = "food_ingred")
    private List<IngredientItem> ingredient = new ArrayList<>();

    @Column(name = "food_cre_date")
    private Date creationDate;

}
