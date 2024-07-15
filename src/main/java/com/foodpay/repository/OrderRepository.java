package com.foodpay.repository;

import com.foodpay.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByCustomerId(long id);
    public List<Order> findByRestaurantId(long id);
}
