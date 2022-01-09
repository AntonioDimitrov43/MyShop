package com.myshop.order.service.interfaces;

import com.myshop.order.model.OrderEntry;

import java.util.List;

public interface OrderEntryService {
    OrderEntry saveOrderEntry(OrderEntry orderEntry);

    List<OrderEntry> getAllByOrder(Long idOrder);
}
