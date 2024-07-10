package com.foodpay.service;

import com.foodpay.model.Category;

import java.util.List;

public interface CategoryService {
    public Category createCategory(String name, long userId) throws Exception;
    public List<Category> findCategoryByRestaurantId(long id) throws Exception;
    public Category findCategoryById(long id) throws Exception;

}
