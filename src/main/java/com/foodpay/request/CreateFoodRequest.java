package com.foodpay.request;

import com.foodpay.model.Category;
import com.foodpay.model.IngredientItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private long price;
    private Category category;
    private List<String> img;
    private boolean available;
    private long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientItem> ingredients;
}
