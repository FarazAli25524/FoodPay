package com.foodpay.service;

import com.foodpay.model.IngredientCategory;
import com.foodpay.model.IngredientItem;

import java.util.List;

public interface IngredientService {
    public IngredientCategory createIngredientCategory(String name, long restaurantId) throws Exception;
    public IngredientCategory findIngredientCategoryById(long id) throws Exception;
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(long id) throws Exception;
    public IngredientItem createIngredientItem(long restaurantId, String name, long categoryId) throws Exception;
    public List<IngredientItem> findRestaurantIngredient(long id) throws Exception;
    public IngredientItem updateStock(long id) throws  Exception;
}
