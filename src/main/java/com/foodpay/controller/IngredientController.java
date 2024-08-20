package com.foodpay.controller;

import com.foodpay.model.IngredientCategory;
import com.foodpay.model.IngredientItem;
import com.foodpay.request.IngredientCategoryRequest;
import com.foodpay.request.IngredientItemRequest;
import com.foodpay.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req
            ) throws Exception{
        IngredientCategory ingredientCategory = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<IngredientItem> createIngredientCategory(
            @RequestBody IngredientItemRequest req
    ) throws Exception{
        IngredientItem ingredientItem = ingredientService.createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(ingredientItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/{stock}")
    public ResponseEntity<IngredientItem> updateIngredientStock(
            @PathVariable long id
    ) throws Exception{
        IngredientItem ingredientItem = ingredientService.updateStock(id);
        return new ResponseEntity<>(ingredientItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItem>> getRestaurantIngredient(
            @PathVariable long id
    ) throws Exception{
        List<IngredientItem> ingredientItem = ingredientService.findRestaurantIngredient(id);
        return new ResponseEntity<>(ingredientItem, HttpStatus.FOUND);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable long id
    ) throws Exception{
        List<IngredientCategory> ingredientItem = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientItem, HttpStatus.FOUND);
    }

}
