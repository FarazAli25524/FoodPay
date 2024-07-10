package com.foodpay.request;

import lombok.Data;

@Data
public class IngredientItemRequest {
    private String name;
    private long restaurantId;
    private long categoryId;
}
