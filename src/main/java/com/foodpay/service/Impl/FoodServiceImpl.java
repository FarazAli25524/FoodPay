package com.foodpay.service.Impl;

import com.foodpay.model.Category;
import com.foodpay.model.Food;
import com.foodpay.model.Restaurant;
import com.foodpay.repository.FoodRepository;
import com.foodpay.request.CreateFoodRequest;
import com.foodpay.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) throws Exception {
       Food food = new Food();
       food.setCategory(category);
       food.setRestaurant(restaurant);
       food.setDescription(req.getDescription());
       food.setImg(req.getImg());
       food.setName(req.getName());
       food.setPrice(req.getPrice());
       food.setIngredients(req.getIngredients());
       food.setSeasonal(req.isSeasonal());
       food.setVegetarian(req.isVegetarian());

       Food savedFood = foodRepository.save(food);
       restaurant.getFoods().add(savedFood);
       return savedFood;
    }

    @Override
    public void deleteFood(long id) throws Exception {
        Food food = findFoodNyId(id);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantsFood(long restaurantId, boolean isVegetarian, boolean isNonVegetarian, boolean isSeasonal, String category) throws Exception {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVegetarian){
            foods = filterByVegetarian(foods, isVegetarian);
        }
        else if(isNonVegetarian){
            foods = filterByNonVegetarian(foods, isNonVegetarian);
        }
        else if (isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }
        else if (category != null && !category.equals("")) {
            foods = filterByCategory(foods, category);
        }

        return foods;
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian){
        return foods.stream().filter(food -> food.isVegetarian()==isVegetarian).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods, boolean isNonVegetarian){
        return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal){
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByCategory(List<Food> foods, String category){
        //return foods.stream().filter(food -> food.getCategory().getName().equals(category)).collect(Collectors.toList());
        return foods.stream().filter(food -> {
            if(food.getCategory() != null){
                return food.getCategory().getName().equals(category);
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) throws Exception {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodNyId(long id) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(id);

        if(optionalFood.isEmpty()){
            throw new Exception("Food doesn't exist!");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(long id) throws Exception {
        Food food = findFoodNyId(id);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
