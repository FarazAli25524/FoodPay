package com.foodpay.service.Impl;

import com.foodpay.model.IngredientCategory;
import com.foodpay.model.IngredientItem;
import com.foodpay.model.Restaurant;
import com.foodpay.repository.IngredientCategoryRepository;
import com.foodpay.repository.IngredientItemRepository;
import com.foodpay.service.IngredientService;
import com.foodpay.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, long restaurantId) throws Exception {
        Restaurant res = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setRestaurant(res);
        ingredientCategory.setName(name);
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(long id) throws Exception {
        Optional<IngredientCategory> ingredientCategory = ingredientCategoryRepository.findById(id);
        if(ingredientCategory.isEmpty()){
            throw new Exception("Ingredient Category not exist!");
        }
        return ingredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientItem createIngredientItem(long restaurantId, String name, long categoryId) throws Exception {
        Restaurant res = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = findIngredientCategoryById(categoryId);
        IngredientItem ingredientItem = new IngredientItem();
        ingredientItem.setName(name);
        ingredientItem.setRestaurant(res);
        ingredientItem.setCategory(ingredientCategory);

        IngredientItem savedIngredientItem = ingredientItemRepository.save(ingredientItem);
        ingredientCategory.getIngredientItems().add(ingredientItem);
        return savedIngredientItem;
    }

    @Override
    public List<IngredientItem> findRestaurantIngredient(long id) throws Exception {
        return ingredientItemRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientItem updateStock(long id) throws Exception {
        Optional<IngredientItem> ingredientItem = ingredientItemRepository.findById(id);
        if(ingredientItem.isEmpty()){
            throw new Exception("Ingredient Item not exist!");
        }
        IngredientItem savedIngredientItem = ingredientItem.get();
        savedIngredientItem.setStock(!savedIngredientItem.isStock());
        return ingredientItemRepository.save(savedIngredientItem);
    }
}
