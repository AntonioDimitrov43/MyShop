package com.myshop.order.service.interfaces;

import com.myshop.order.model.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Orders createOrder(Orders orders);

    List<Orders> findAllForUser(String username);

    Optional<Orders> findByIdForUser(Long id, String username);
}
