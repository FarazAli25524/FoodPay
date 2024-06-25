package com.foodpay.service;

import com.foodpay.dto.RestaurantDto;
import com.foodpay.model.Restaurant;
import com.foodpay.model.User;
import com.foodpay.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRequest) throws Exception;
    public void deleteRestaurant(Long restaurantId) throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant searchRestaurantById(Long id) throws Exception;
    public Restaurant getRestaurantByUserId(Long id) throws Exception;
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
    public Restaurant findRestaurantById(Long id) throws Exception;
}
