package com.myshop.order.repository;

import com.myshop.order.model.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderEntryRepository extends JpaRepository<OrderEntry, Long> {

    List<OrderEntry> findAllByOrderId(Long id);

}
