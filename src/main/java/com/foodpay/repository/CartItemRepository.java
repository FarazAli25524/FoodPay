package com.foodpay.repository;

import com.foodpay.model.Cart;
import com.foodpay.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public Cart findByCustomerId(long userId);
}
