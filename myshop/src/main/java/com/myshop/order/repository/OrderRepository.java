package com.myshop.order.repository;

import com.myshop.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByUserUsername(String username);

    Optional<Orders> findOrderByIdAndUserUsername(Long id, String username);
}
