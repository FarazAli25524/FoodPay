package com.foodpay.controller;

import com.foodpay.model.Food;
import com.foodpay.model.Restaurant;
import com.foodpay.model.User;
import com.foodpay.request.CreateFoodRequest;
import com.foodpay.service.FoodService;
import com.foodpay.service.RestaurantService;
import com.foodpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(
            @RequestParam String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.searchFood(keyword);
        return new ResponseEntity<>(food, HttpStatus.FOUND);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonVegetarian,
            @RequestParam(required = false) String category,
            @PathVariable long restaurantId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.getRestaurantsFood(restaurantId, vegetarian, nonVegetarian, seasonal, category);
        return new ResponseEntity<>(food, HttpStatus.FOUND);
    }

}

