package com.myshop.cart.service;

import com.myshop.cart.model.CartItem;

import java.util.List;

public interface CartItemService {

    /**
     * Checks and finds all cart items assigned to a valid username if such a username exists
     * @param username not null or blank username
     */
    List<CartItem> getCartForUser(String username);

    /**
     * If person with username and specific product exist it adds them to the cart list.
     * @param cartItem not null cart item
     */
    CartItem saveCartItem(CartItem cartItem);

    /**
     * Sends all carts items, that specific user with existing username has in their cart, to the order entry.
     * @param username not null and not blank username
     */
    void moveToOrderEntryByUsername(String username);
}
