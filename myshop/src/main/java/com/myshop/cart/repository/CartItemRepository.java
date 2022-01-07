package com.myshop.cart.repository;

import com.myshop.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    List<CartItem> findAllByUserUsername(String username);
}
