package com.foodpay.service;

import com.foodpay.model.Cart;
import com.foodpay.model.CartItem;
import com.foodpay.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;
    public CartItem updateCartItemQuantity(long cartItemId, int quantity) throws Exception;
    public Cart removeItemFromCart(long cartItemId, String jwt) throws Exception;
    public long calculateCartTotal(Cart cart) throws Exception;
    public Cart findCartById(long id) throws Exception;
    public Cart findCartByUserId(String jwt) throws Exception;
    public Cart clearCart(String jwt) throws Exception;
}
