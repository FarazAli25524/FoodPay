package com.foodpay.repository;

import com.foodpay.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findByRestaurantId(long id);
}
