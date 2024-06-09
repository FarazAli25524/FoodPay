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
    private long id;

    private String name;

    private String description;

    private long price;

    @ManyToOne
    private Category category;

    @ElementCollection
    @Column(length = 1000)
    private List<String> img;

    private boolean available;

    @ManyToOne
    private Restaurant restaurant;

    private boolean vegetarian;

    private boolean seasonal;

    @ManyToMany
    private List<IngredientItem> ingredients = new ArrayList<>();

    private Date createdDate;

}
