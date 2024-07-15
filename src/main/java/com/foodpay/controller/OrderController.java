package com.foodpay.controller;

import com.foodpay.model.Order;
import com.foodpay.model.User;
import com.foodpay.request.OrderRequest;
import com.foodpay.service.OrderService;
import com.foodpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(
            @RequestBody OrderRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req, user);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(order, HttpStatus.FOUND);
    }

}
