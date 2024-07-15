package com.foodpay.service.Impl;

import com.foodpay.model.Cart;
import com.foodpay.model.CartItem;
import com.foodpay.model.Food;
import com.foodpay.model.User;
import com.foodpay.repository.CartItemRepository;
import com.foodpay.repository.CartRepository;
import com.foodpay.request.AddCartItemRequest;
import com.foodpay.service.CartService;
import com.foodpay.service.FoodService;
import com.foodpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getCartItems()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setIngredients(req.getIngredient());
        cartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        cart.getCartItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
            throw new Exception("Cart Item not exist!");
        }

        CartItem updateCartItem = cartItem.get();
        updateCartItem.setQuantity(quantity);
        updateCartItem.setTotalPrice(updateCartItem.getFood().getPrice()*quantity);
        return cartItemRepository.save(updateCartItem);
    }

    @Override
    public Cart removeItemFromCart(long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("Cart Item not exist!");
        }

        CartItem savedCartItem = cartItemOptional.get();
        cart.getCartItems().remove(savedCartItem);
        return cartRepository.save(cart) ;
    }

    @Override
    public long calculateCartTotal(Cart cart) throws Exception {
        long total = 0L;
        for(CartItem cartItem : cart.getCartItems()){
            total += (cartItem.getTotalPrice() * cartItem.getQuantity());
        }
        return total;
    }

    @Override
    public Cart findCartById(long id) throws Exception {
        Optional<Cart> cartOptional = cartRepository.findById(id);

        if(cartOptional.isEmpty()){
            throw new Exception("Cart not exist!");
        }
        return cartOptional.get();
    }

    @Override
    public Cart findCartByUserId(long id) throws Exception {
//        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(id);
        cart.setTotal(calculateCartTotal(cart));
        return cart;
    }

    @Override
    public Cart clearCart(long id) throws Exception {
        Cart cart = findCartByUserId(id);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
