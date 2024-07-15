package com.foodpay.controller;

import com.foodpay.model.Order;
import com.foodpay.model.User;
import com.foodpay.service.OrderService;
import com.foodpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Order>> geOrderHistory(
            @PathVariable long id,
            @RequestParam(required = false) String status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> order = orderService.getRestaurantOrder(id, status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{status}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable long id,
            @PathVariable String status,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.updateOrder(id, status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
