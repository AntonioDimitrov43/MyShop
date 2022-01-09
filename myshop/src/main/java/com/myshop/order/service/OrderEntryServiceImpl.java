package com.myshop.order.service;

import com.myshop.exceptions.ElementNotFoundException;
import com.myshop.order.model.OrderEntry;
import com.myshop.order.repository.OrderEntryRepository;
import com.myshop.order.repository.OrderRepository;
import com.myshop.order.service.interfaces.OrderEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEntryServiceImpl implements OrderEntryService {

    private final OrderEntryRepository orderEntryRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderEntryServiceImpl(OrderEntryRepository orderEntryRepository, OrderRepository orderRepository) {
        this.orderEntryRepository = orderEntryRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntry saveOrderEntry(OrderEntry orderEntry) {
        if (orderEntry == null){

            throw new IllegalArgumentException("Orders entry cannot be null!");
        }

        if (!orderRepository.existsById(orderEntry.getOrderId())){

            throw new ElementNotFoundException("Orders with id = " +orderEntry.getOrderId() +" not exist!");
        }

        return orderEntryRepository.save(orderEntry);
    }

    @Override
    public List<OrderEntry> getAllByOrder(Long idOrder) {
        return orderEntryRepository.findAllByOrderId(idOrder);
    }
}
