package com.foodpay.service.Impl;

import com.foodpay.model.*;
import com.foodpay.repository.*;
import com.foodpay.request.OrderRequest;
import com.foodpay.service.CartService;
import com.foodpay.service.OrderService;
import com.foodpay.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippingAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
        Order createOrder = new Order();
        createOrder.setCustomer(user);
        createOrder.setCreatedDate(new Date());
        createOrder.setStatus("PENDING");
        createOrder.setAddress(savedAddress);
        createOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for(CartItem cartItem : cart.getCartItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        long totalPrice = cartService.calculateCartTotal(cart);
        createOrder.setOrderItem(orderItems);
        createOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(createOrder);
        restaurant.getOrders().add(savedOrder);

        return createOrder;
    }

    @Override
    public Order updateOrder(long id, String status) throws Exception {
        Order order = findOrderById(id);

        if(status.equals("OUT_FOR_DELIVERY") || status.equals("DELIVERED") || status.equals("COMPLETED") || status.equals("PENDING")){
            order.setStatus(status);
            return orderRepository.save(order);
        }
        throw new Exception("Order Status Mismatch!");
    }

    @Override
    public void cancelOrder(long id) throws Exception {
        Order order = findOrderById(id);
        orderRepository.deleteById(id);

    }

    @Override
    public List<Order> getUserOrder(long id) throws Exception {
        return orderRepository.findByCustomerId(id);
    }

    @Override
    public List<Order> getRestaurantOrder(long id, String status) throws Exception {
        List<Order> order =  orderRepository.findByRestaurantId(id);
        if(status != null){
            order = order.stream().filter(orders -> orders.getStatus().equals(status)).toList();
        }
        return order;
    }

    @Override
    public Order findOrderById(long id) throws Exception {
        Optional<Order> order = orderRepository.findById(id);

        if(order.isEmpty()){
            throw new Exception("Order not exist!");
        }
        return order.get();
    }
}
