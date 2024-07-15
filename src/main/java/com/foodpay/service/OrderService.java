package com.foodpay.service;

import com.foodpay.model.Order;
import com.foodpay.model.User;
import com.foodpay.request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;
    public Order updateOrder(long id, String status) throws Exception;
    public void cancelOrder(long id) throws Exception;
    public List<Order> getUserOrder(long id) throws Exception;
    public List<Order> getRestaurantOrder(long id, String status) throws Exception;
    public Order findOrderById(long id) throws Exception;
}
