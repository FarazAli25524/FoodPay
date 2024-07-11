package com.foodpay.service;

import com.foodpay.model.Category;
import com.foodpay.model.Food;
import com.foodpay.model.Restaurant;
import com.foodpay.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception;
    public void deleteFood(long id) throws Exception;
    public List<Food> getRestaurantsFood(long restaurantId, boolean isVegetarian, boolean isNonvegetarian, boolean isSeasonal, String category) throws Exception;
    public List<Food> searchFood(String keyword) throws Exception;
    public Food findFoodById(long id) throws Exception;
    public Food updateAvailabilityStatus(long id) throws Exception;
}
