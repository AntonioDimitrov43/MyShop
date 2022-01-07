package com.myshop.cart.service;

import com.myshop.cart.model.CartItem;

import java.util.List;

public interface CartItemService {


    List<CartItem> getCart(String username);

    CartItem saveCartItem(CartItem cartItem);
}
