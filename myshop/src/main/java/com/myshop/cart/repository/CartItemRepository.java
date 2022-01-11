package com.myshop.cart.repository;

import com.myshop.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    /**
     * Finds all cart items that are saved to a specific username
     * @param username valid username
     * @return a list of all cart items found
     */
    List<CartItem> findAllByUserUsername(String username);
}
