package com.foodpay.service.Impl;

import com.foodpay.model.Category;
import com.foodpay.model.Restaurant;
import com.foodpay.repository.CategoryRepository;
import com.foodpay.service.CategoryService;
import com.foodpay.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, long userId) throws Exception {
        Restaurant rest = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(rest);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(long id) throws Exception {
        Optional<Category> optional = categoryRepository.findById(id);

        if(optional.isEmpty()){
            throw new Exception("Category not found!");
        }
        return optional.get();
    }
}
